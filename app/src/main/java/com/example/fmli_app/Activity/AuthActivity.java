package com.example.fmli_app.Activity;

import static com.example.fmli_app.Activity.SplashActivity.APP_PREFERENCES;
import static com.example.fmli_app.Activity.SplashActivity.LOGIN;
import static com.example.fmli_app.Activity.SplashActivity.PASSWORD;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fmli_app.DB.Database;
import com.example.fmli_app.DB.users.User;
import com.example.fmli_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class AuthActivity extends AppCompatActivity {
    EditText emailnum, password;
    Button auth;
    TextView register;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Отключение заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_auth);

        // Подключение к базе данных
        Database db = new Database(this);

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
        auth.setOnClickListener(view -> {
            // Авторизация

            String login = emailnum.getText().toString();
            String pass = password.getText().toString();

            // Если пользователь найден, то переходит к MainActivity
            User user = db.selectUser(login, pass);
            if (user != null) {
                sharedPreferencesEditor.putString(LOGIN, login);
                sharedPreferencesEditor.putString(PASSWORD, pass);
                sharedPreferencesEditor.commit();
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                this.finish();
            } else {
                Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
            }

        });
        register.setOnClickListener(view -> {
            // Переход к RegisterActivity
            Intent intent = new Intent(this, RegisterActivity.class);
            this.startActivity(intent);
        });
    }
}
