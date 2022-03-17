package com.example.fmli_app.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fmli_app.Fragment.BottomNavigationDrawerFragment;
import com.example.fmli_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    AppBarConfiguration navBarConfiguration;
    BottomNavigationView navbar;
    NavigationView navdrawer;
    NavController navcontroller;
    NavHostFragment navhost;
    FragmentManager fragmentManager;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Отключение заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Отключение панели действий
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);


        // Установка нижней панели навигации
        navbar = findViewById(R.id.bottom_navigation);
        navcontroller = Navigation.findNavController(this, R.id.maingraph);
        NavigationUI.setupWithNavController(navbar, navcontroller);


        navbar.setOnItemSelectedListener((menuItem) -> {
            if (menuItem.getItemId() == R.id.page_add) {
                BottomNavigationDrawerFragment navigationView = new BottomNavigationDrawerFragment();
                navigationView.show(getSupportFragmentManager(), navigationView.getTag());
            }
            return true;
        });
    }


}
