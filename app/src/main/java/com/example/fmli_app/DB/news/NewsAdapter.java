package com.example.fmli_app.DB.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.fmli_app.R;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<NewsItem> {

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
        TextView likes = convertView.findViewById(R.id.likes_count);
        TextView comments = convertView.findViewById(R.id.comments_count);
        TextView watched = convertView.findViewById(R.id.watched_count);
        ListView tagsList = convertView.findViewById(R.id.tags_list);

//        avatar.setImageURI(data.getAuthor().getAvatar_url());
        username.setText(data.getAuthor().getCreation_date());

        // Если содержимое текста статьи больше, чем заверено, скрывает остальной текст.
        if (data.getText().length() <= 64) {
            text.setText(data.getText());
        } else {
            text.setText(data.getText().substring(0, 62) + "...");
        }
        date.setText(data.getDate());
        title.setText(data.getTitle());

        // Если изображения нет, то скрывает ImageView:news_picture
        if (data.getURL() == null || data.getURL().equals("")) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) picture.getLayoutParams();
            params.height = 0;
            picture.setLayoutParams(params);
        }
//        picture.setImageURI(data.getURL());
//        likes.setText(data.get);
//        comments.setText(data.get);
//        watched.setText(data.get);
//        tagsList;

        return convertView;
    }
}
