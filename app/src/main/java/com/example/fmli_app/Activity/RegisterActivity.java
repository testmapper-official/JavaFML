package com.example.fmli_app.Activity;

import static com.example.fmli_app.Activity.SplashActivity.APP_PREFERENCES;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fmli_app.DB.users.User;
import com.example.fmli_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {
    EditText emailnum, password;
    Button getcode, loginchanger;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;
    boolean loginIsNum = true;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Отключение заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register);

        // Подключение к базе данных
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference(User.key);

        // Получение SharedPreferences
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        // Получение XML фронтенд виджетов
        emailnum = this.findViewById(R.id.emailnum);
        password = this.findViewById(R.id.password);
        getcode = this.findViewById(R.id.getcode);
        loginchanger = this.findViewById(R.id.loginchanger);

        // Подключение бекенд функций
        getcode.setOnClickListener(view -> {
            // Регистрация
            String email = emailnum.getText().toString();
            String pass = password.getText().toString();
            if (pass.length() < 6) {
                Toast.makeText(this, "Пароль должен состоять не менее из 6 символов", Toast.LENGTH_LONG).show();
            } else {
                // Регистрация пользователя в базе данных
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Переход к MainActivity
                        String id = mAuth.getUid();
                        User user = new User(id, email,"", "", 0);
                        mDatabase.push().setValue(user);

                        Toast.makeText(this, "Ваша учетная запись была успешно создана", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        this.startActivity(intent);
                    } else {
                        Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
