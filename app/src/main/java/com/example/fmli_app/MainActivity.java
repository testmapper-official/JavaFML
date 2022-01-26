package com.example.fmli_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    AppBarConfiguration navBarConfiguration;
    BottomNavigationView navbar;
    NavController navcontroller;
    NavHostFragment navhost;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*fragmentManager = getSupportFragmentManager();
        navhost = (NavHostFragment) fragmentManager.findFragmentById(R.id.navgraph);
        navcontroller = navhost.getNavController();
        navbar = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(navbar, navcontroller);*/

        navbar = findViewById(R.id.bottom_navigation);
        navcontroller = Navigation.findNavController(this,  R.id.maingraph);
        NavigationUI.setupWithNavController(navbar, navcontroller);
        
    }
}