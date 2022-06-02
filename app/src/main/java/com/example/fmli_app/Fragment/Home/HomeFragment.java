package com.example.fmli_app.Fragment.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fmli_app.DB.news.NewsAdapter;
import com.example.fmli_app.DB.news.NewsItem;
import com.example.fmli_app.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Подключение к базе данных
        mDatabase = FirebaseDatabase.getInstance().getReference(NewsItem.key);

        // Инициализаци ListView фрагмента уведомлений
        ArrayList<NewsItem> data = new ArrayList<>();
        ListView newsView = view.findViewById(R.id.news_list);
        NewsAdapter arrayAdapter = new NewsAdapter(getContext(), data);

        mDatabase.addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded( @NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewsItem newsItem = new NewsItem();//snapshot.getValue(NewsItem.class);
                assert newsItem != null;
                data.add(newsItem);
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged( @NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildRemoved( @NonNull DataSnapshot snapshot) {
                data.remove(snapshot.getValue(NewsItem.class));
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved( @NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled( @NonNull DatabaseError error) {
            }
        });

        newsView.setAdapter(arrayAdapter);

        return view;
    }
}
