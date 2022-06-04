package com.example.fmli_app.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.fmli_app.DB.news.NewsItem;
import com.example.fmli_app.DB.users.User;
import com.example.fmli_app.Fragment.Home.Comment;
import com.example.fmli_app.Fragment.Home.CommentAdapter;
import com.example.fmli_app.R;
import com.example.fmli_app.Simple;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;


public class NewsActivity extends AppCompatActivity {
    ArrayList<Comment> data = new ArrayList<>();
    ListView listView;
    ArrayAdapter<Comment> adapter;
    ImageButton btnBack;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;

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

        setContentView(R.layout.activity_news);

        Gson gson = new Gson();
        String js = getIntent().getStringExtra("NewsItem");
        NewsItem newsItem = gson.fromJson(js, NewsItem.class);

        // Установка содержимого уведомления
        btnBack = findViewById(R.id.back);
        ImageButton send = findViewById(R.id.send);
        EditText addComment = findViewById(R.id.add_comment);

        ImageView avatar = findViewById(R.id.avatar);
        TextView username = findViewById(R.id.news_username);
        TextView date = findViewById(R.id.news_date);
        TextView title = findViewById(R.id.news_title);
        TextView text = findViewById(R.id.news_text);
        ImageView picture = findViewById(R.id.news_picture);

        // Кнопка назад
        btnBack.setOnClickListener(view -> {
            finish();
        });

        // Отправить комментарий
        send.setOnClickListener(view -> {
            String cText = addComment.getText().toString();
            if (!TextUtils.isEmpty(cText)) {
                Date now = new Date(System.currentTimeMillis());
                String id = Simple.Cid(now.getTime());
                String uid = mAuth.getUid();
                String nid = newsItem.getId();
                Comment comment = new Comment(id, uid, nid, cText);
                addComment.setText("");

                mDatabase.child(Comment.key).push().setValue(comment);
            } else {
                Toast.makeText(getApplicationContext(), "Заполните комментарий", Toast.LENGTH_SHORT).show();
            }

        });

        // Подключение к базе данных
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mDatabase.child(User.key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot e : dataSnapshot.getChildren()) {
                    User user = e.getValue(User.class);
                    assert user != null;
                    if (user.getId().equals(newsItem.getUid())) {
                        username.setText(user.getNickname());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //avatar.setImageURI(user.getAvatar_url());

        text.setText(newsItem.getText());

        date.setText(newsItem.getDate());
        title.setText(newsItem.getTitle());

        // Если изображения нет, то скрывает ImageView:news_picture
        if (newsItem.getUrl() == null || newsItem.getUrl().equals("")) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) picture.getLayoutParams();
            params.height = 0;
            picture.setLayoutParams(params);
        }

        listView = findViewById(R.id.koment_text);
        adapter = new CommentAdapter(this, data);

        mDatabase.child(Comment.key).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Comment comment = snapshot.getValue(Comment.class);
                assert comment != null;
                if (comment.getNid().equals(newsItem.getId())) {
                    data.add(comment);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Comment comment = snapshot.getValue(Comment.class);
                assert comment != null;
                if (comment.getNid().equals(newsItem.getId())) {
                    data.remove(snapshot.getValue(Comment.class));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        listView.setAdapter(adapter);

    }
}