package com.example.fmli_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fmli_app.Fragment.Service.DBNotes;
import com.example.fmli_app.Fragment.Service.Notes;
import com.example.fmli_app.R;
import com.google.gson.Gson;

public class NotesEditActivity extends AppCompatActivity {
    TextView editBody, editHead;
    ImageButton btnBack, btnDelete, btnSave;
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

        setContentView(R.layout.activity_notes_edit);

        editHead = findViewById(R.id.Zagolov);
        editBody = findViewById(R.id.globaltext);
        btnDelete = findViewById(R.id.delite);
        btnBack = findViewById(R.id.back);
        btnSave = findViewById(R.id.save);

        // Подключение к базе данных
        db = new DBNotes(getApplicationContext());

        Gson gson = new Gson();
        String js = getIntent().getStringExtra("Notes");
        Notes notes = gson.fromJson(js, Notes.class);

        editHead.setText(notes.getHead());
        editBody.setText(notes.getBody());

        btnBack.setOnClickListener(view -> {
            finish();
        });

        btnDelete.setOnClickListener(view -> {
            db.delete(notes.getId());
            Intent i = new Intent();
            setResult(RESULT_OK, i);
            finish();
        });

        btnSave.setOnClickListener(view -> {
            notes.setBody(editBody.getText().toString());
            notes.setHead(editHead.getText().toString());
            db.update(notes);
            Intent i = new Intent();
            setResult(RESULT_OK, i);
            finish();
        });


    }
}