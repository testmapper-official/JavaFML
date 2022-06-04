package com.example.fmli_app.Fragment.Service;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.fmli_app.Activity.NotesActivity;
import com.example.fmli_app.Activity.NotesEditActivity;
import com.example.fmli_app.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ServiceFragment extends Fragment {
    ArrayList<Notes> data;
    ImageButton button;
    ListView listView;
    ArrayAdapter<Notes> adapter;
    DBNotes db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);

        // Подключение к базе данных
        db = new DBNotes(getContext());

        // Инициализация ArrayList фрагмента уведомлений
        data = db.selectAll();


        listView = view.findViewById(R.id.listView);
        // Создание адаптера.
        adapter = new NotesAdapter(getContext(), data);

        listView.setAdapter(adapter);

        button = view.findViewById(R.id.bat);
        button.setOnClickListener(v -> {
            // Переход к NotesActivity как отдельное окно.
            Intent intent = new Intent(getContext(), NotesActivity.class);
            startActivityForResult(intent, 1);
        });

        // Выведение системного уведомления при нажатии элемента фрагмента уведомлений
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            //Получаем объект класса Notes
            Notes notes = (Notes) adapterView.getAdapter().getItem(i);

            // Переход к NewsEditActivity (NotesActivity) как отдельное окно.
            Intent intent = new Intent(getContext(), NotesEditActivity.class);
            //Преобразую класс Notes в gson

            Gson gson = new Gson();
            intent.putExtra("Notes", gson.toJson(notes));

            startActivityForResult(intent, 1);

        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (resultCode) {
            case RESULT_OK:
                data.clear();
                for (Notes e : db.selectAll()) {
                    data.add(e);
                }
                adapter.notifyDataSetChanged();
        }
    }
}