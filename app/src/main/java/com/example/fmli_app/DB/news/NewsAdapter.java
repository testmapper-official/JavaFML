package com.example.fmli_app.DB.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        ImageView imageView = convertView.findViewById(R.id.notification_image);
        TextView textView = convertView.findViewById(R.id.notification_text);
        TextView datatextView = convertView.findViewById(R.id.notification_textdata);

        return convertView;
    }
}
