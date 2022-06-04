package com.example.fmli_app.Activity;

import static com.example.fmli_app.Activity.SplashActivity.APP_PREFERENCES;
import static com.example.fmli_app.Activity.SplashActivity.LOGIN;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fmli_app.DB.users.User;
import com.example.fmli_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class AuthActivity extends AppCompatActivity {
    EditText emailnum, password;
    Button auth;
    TextView register;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;
    DatabaseReference mDatabase;
    User user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Отключение заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_auth);

        // Подключение к базе данных
        mAuth = FirebaseAuth.getInstance();

        // Получение SharedPreferences
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        // Получение XML фронтенд виджетов
        emailnum = this.findViewById(R.id.emailnum);
        password = this.findViewById(R.id.password);
        register = this.findViewById(R.id.register);
        auth = this.findViewById(R.id.auth);

        emailnum.setText(sharedPreferences.getString(LOGIN, ""));

        // Подключение бекенд функций
        password.setOnKeyListener((v, keyCode, event) -> {
            // Если пользователь завершает ввод
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                auth();

                return true;
            }
            return false;
        });
        auth.setOnClickListener(view -> auth());
        register.setOnClickListener(view -> {
            // Переход к RegisterActivity
            Intent intent = new Intent(this, RegisterActivity.class);
            this.startActivity(intent);
        });
    }

    private void auth() {
        // Авторизация

        String login = emailnum.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(pass) || TextUtils.isEmpty(login)) {
            Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(login, pass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                this.finish();
            } else {
                Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
