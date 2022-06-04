package com.example.fmli_app.Fragment.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fmli_app.DB.news.NewsItem;
import com.example.fmli_app.Fragment.Home.Comment;
import com.example.fmli_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileCardFragment extends Fragment {
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profilecard, container, false);

        // Контент

        TextView postCount = view.findViewById(R.id.posts_count);
        TextView commentCount = view.findViewById(R.id.comments_count);

        // Подключение к базе данных
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mDatabase.child(Comment.key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = 0;
                for (DataSnapshot e : dataSnapshot.getChildren()) {
                    Comment comment = e.getValue(Comment.class);
                    assert comment != null;
                    if (comment.getUid().equals(mAuth.getUid())) {
                        count++;
                    }
                }
                commentCount.setText(Integer.toString(count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.child(NewsItem.key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = 0;
                for (DataSnapshot e : dataSnapshot.getChildren()) {
                    NewsItem newsItem = e.getValue(NewsItem.class);
                    assert newsItem != null;
                    if (newsItem.getUid().equals(mAuth.getUid())) {
                        count++;

                    }
                }
                postCount.setText(Integer.toString(count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
