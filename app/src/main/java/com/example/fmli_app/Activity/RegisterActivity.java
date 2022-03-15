package com.example.fmli_app.Activity;

import static com.example.fmli_app.Activity.SplashActivity.APP_PREFERENCES;
import static com.example.fmli_app.Activity.SplashActivity.LOGIN;
import static com.example.fmli_app.Activity.SplashActivity.PASSWORD;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fmli_app.DB.Database;
import com.example.fmli_app.DB.users.User;
import com.example.fmli_app.R;

import java.util.Date;


public class RegisterActivity extends AppCompatActivity {
    EditText emailnum, password;
    Button getcode, loginchanger;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;
    boolean loginIsNum = true;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Отключение заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register);

        // Подключение к базе данных
        db = new Database(this);

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
            String login = emailnum.getText().toString();

            if (!db.hasUser(login)) {

                String pass = password.getText().toString();
                Date date = new Date(System.currentTimeMillis());

                String email = null;
                String number = null;

                if (!loginIsNum) {
                    email = login;
                } else {
                    number = login;
                }
                // Регистрация пользователя в базе данных
                User user = new User(pass, email, number, null, null, null, date.toString(), null, 0);
                db.insert(user);

                // Запомнить данные пользователя в приложении
                sharedPreferencesEditor.putString(LOGIN, login);
                sharedPreferencesEditor.putString(PASSWORD, pass);
                sharedPreferencesEditor.commit();

                // Переход к MainActivity
                Toast.makeText(this, "Ваша учетная запись была успешно создана", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intent);
            } else {
                Toast.makeText(this, "Такая учетная запись уже существует", Toast.LENGTH_SHORT).show();
            }
        });
        loginchanger.setOnClickListener(view -> {
            if (loginIsNum) {
                emailnum.setHint(R.string.email);
                loginchanger.setText(this.getString(R.string.changebtn_email));
                emailnum.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            } else {
                emailnum.setHint(R.string.phone);
                loginchanger.setText(this.getString(R.string.changebtn_phone));
                emailnum.setInputType(InputType.TYPE_CLASS_PHONE);
            }
            loginIsNum = !loginIsNum;
        });
    }
}
