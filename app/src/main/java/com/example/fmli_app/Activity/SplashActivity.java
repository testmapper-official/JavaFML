package com.example.fmli_app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fmli_app.DB.Database;
import com.example.fmli_app.DB.users.User;
import com.example.fmli_app.R;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1;

    SharedPreferences sharedPreferences;
    final String LOGIN = "hash_user_login54";
    final String PASSWORD = "hash_user_password81";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Подключение к базе данных
        Database db = new Database(this);

        // Задержка перед запуском приложения (минимальная + "холодный старт")
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, AuthActivity.class);
            sharedPreferences = getPreferences(MODE_PRIVATE);
            String savedLogin = sharedPreferences.getString(LOGIN, "");
            String savedPassword = sharedPreferences.getString(PASSWORD, "");

            savedLogin = "123";
            savedPassword = "123";

            // Если пользователь найден, то переходит к MainActivity
            User user = db.selectUser(savedLogin, savedPassword);
            if (user == null) {
                intent = new Intent(this, MainActivity.class);
            }

            this.startActivity(intent);
            this.finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}