package com.example.fmli_app.DB.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.fmli_app.DB.users.User;
import com.example.fmli_app.Fragment.Home.Comment;
import com.example.fmli_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<NewsItem> {
    DatabaseReference mDatabase;

    public NewsAdapter(Context context, ArrayList<NewsItem> data) {
        super(context, R.layout.item_news, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsItem data = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_news, null);
        }


        // Установка содержимого уведомления
        ImageView avatar = convertView.findViewById(R.id.avatar);
        TextView username = convertView.findViewById(R.id.news_username);
        TextView date = convertView.findViewById(R.id.news_date);
        TextView title = convertView.findViewById(R.id.news_title);
        TextView text = convertView.findViewById(R.id.news_text);
        ImageView picture = convertView.findViewById(R.id.news_picture);
        TextView comments = convertView.findViewById(R.id.comments_count);

        comments.setText("0");

        // Подключение к базе данных
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child(User.key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot e : dataSnapshot.getChildren()) {
                    User user = e.getValue(User.class);
                    assert user != null;
                    if (user.getId().equals(data.getUid())) {
                        username.setText(user.getNickname());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.child(Comment.key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = 0;
                for (DataSnapshot e : dataSnapshot.getChildren()) {
                    Comment comment = e.getValue(Comment.class);
                    assert comment != null;
                    if (comment.getNid().equals(data.getId())) {
                        count++;
                        comments.setText(Integer.toString(count));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Если содержимое текста статьи больше, чем заверено, скрывает остальной текст.
        if (data.getText().length() <= 64) {
            text.setText(data.getText());
        } else {
            text.setText(data.getText().substring(0, 62) + "...");
        }
        date.setText(data.getDate());
        title.setText(data.getTitle());

        // Если изображения нет, то скрывает ImageView:news_picture
        if (data.getUrl() == null || data.getUrl().equals("")) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) picture.getLayoutParams();
            params.height = 0;
            picture.setLayoutParams(params);
        }

        return convertView;
    }
}
