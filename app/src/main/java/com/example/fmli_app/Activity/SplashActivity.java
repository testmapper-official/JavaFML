package com.example.fmli_app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fmli_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1;
    SharedPreferences sharedPreferences;
    public static final String APP_PREFERENCES = "apppref";
    public static final String LOGIN = "hash_user_login54"; // Эл. почта или телефон
    public static final String PASSWORD = "hash_user_password81";
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            this.finish();
        } else {
            Intent intent = new Intent(this, AuthActivity.class);
            this.startActivity(intent);
            this.finish();
        }
        }
}