package com.example.fmli_app.Fragment.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.fmli_app.DB.users.User;
import com.example.fmli_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommentAdapter extends ArrayAdapter<Comment> {
    DatabaseReference mDatabase;

    public CommentAdapter(Context context, ArrayList<Comment> data) {
        super(context, R.layout.item_komit, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment data = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_komit, null);
        }

        TextView username = convertView.findViewById(R.id.adres_1);
        TextView commentText = convertView.findViewById(R.id.text_koment);

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


        //Заполнение форма
        username.setText(data.getNid());
        commentText.setText(data.getText());

        return convertView;
    }
}
