package com.example.fmli_app.Fragment.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fmli_app.DB.Database;
import com.example.fmli_app.DB.news.NewsAdapter;
import com.example.fmli_app.DB.news.NewsItem;
import com.example.fmli_app.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Подключение к базе данных
        Database db = new Database(getContext());

        // Инициализаци ListView фрагмента уведомлений
        ArrayList<NewsItem> data = db.selectAllNews();

        ListView newsView = view.findViewById(R.id.news_list);
        NewsAdapter arrayAdapter = new NewsAdapter(getContext(), data);
        newsView.setAdapter(arrayAdapter);

        Toast.makeText(getContext(), Integer.toString(data.size()), Toast.LENGTH_SHORT).show();

        return view;
    }
}
