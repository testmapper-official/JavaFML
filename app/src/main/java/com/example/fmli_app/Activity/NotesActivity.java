package com.example.fmli_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fmli_app.Fragment.Service.DBNotes;
import com.example.fmli_app.R;

public class NotesActivity extends AppCompatActivity {
    EditText editBody;
    EditText editHead;
    ImageButton batAddNotes, batGoToBack;

    DBNotes db;

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

        setContentView(R.layout.activity_new_notes);

        batAddNotes = findViewById(R.id.save);
        batGoToBack = findViewById(R.id.back);
        editHead = findViewById(R.id.Zagolov);
        editBody = findViewById(R.id.globaltext);

        // Подключение к базе данных
        db = new DBNotes(getApplicationContext());

        batAddNotes.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Запись создана", Toast.LENGTH_LONG).show();
            db.insert(editHead.getText().toString(), editBody.getText().toString());
            Intent i = new Intent();
            setResult(RESULT_OK, i);
            finish();
        });

        batGoToBack.setOnClickListener(view -> {
            finish();
        });
    }
}
