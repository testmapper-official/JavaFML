package com.example.fmli_app.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static com.example.fmli_app.Activity.SplashActivity.APP_PREFERENCES;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.fmli_app.DB.news.NewsItem;
import com.example.fmli_app.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class BottomNavigationDrawerFragment extends BottomSheetDialogFragment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;
    DatabaseReference mDatabase;

    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottomsheet, container, false);

        // Подключение к базе данных
        mDatabase = FirebaseDatabase.getInstance().getReference(NewsItem.key);

        // Получение SharedPreferences
        sharedPreferences = getContext().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);

        NavigationView nav_view = view.findViewById(R.id.navigation_drawer);

        nav_view.setNavigationItemSelectedListener((menuItem) -> {
            switch (menuItem.getItemId()) {
                case R.id.app_bar_add_comm:
                    //
                    break;
                case R.id.app_bar_add_notify:
                    //
                    break;
                case R.id.app_bar_add_news:

                    Timestamp timestamp = new Timestamp(new Date(System.currentTimeMillis()));

                    NewsItem newsItem = new NewsItem(0, "", timestamp, getString(R.string.empty_text), getString(R.string.app_name));
                    Toast.makeText(getContext(), "Вы успешно опубликовали статью", Toast.LENGTH_LONG).show();
                    mDatabase.push().setValue(newsItem);
                    break;
                case R.id.app_bar_add_tag:
                    //
                    break;
                case R.id.app_bar_settings:
                    //
                    break;
            }
            dismiss();
            return true;
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setWhiteNavigationBar(@NonNull Dialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            window.getWindowManager().getDefaultDisplay().getMetrics(metrics);

            GradientDrawable dimDrawable = new GradientDrawable();
            // ...customize your dim effect here

            GradientDrawable navigationBarDrawable = new GradientDrawable();
            navigationBarDrawable.setShape(GradientDrawable.RECTANGLE);
            navigationBarDrawable.setColor(Color.WHITE);

            Drawable[] layers = {dimDrawable, navigationBarDrawable};

            LayerDrawable windowBackground = new LayerDrawable(layers);
            windowBackground.setLayerInsetTop(1, metrics.heightPixels);

            window.setBackgroundDrawable(windowBackground);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setWhiteNavigationBar(dialog);
        }

        return dialog;
    }
}
