package com.example.fmli_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fmli_app.R;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Задержка перед запуском приложения (минимальная + "холодный старт")
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            this.finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}