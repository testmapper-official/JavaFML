package com.example.fmli_app.DB.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fmli_app.R;

import java.util.ArrayList;

public class NotificationAdapter extends ArrayAdapter<NotificationItem> {

    public NotificationAdapter(Context context, ArrayList<NotificationItem> data) {
        super(context, R.layout.item_notification, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NotificationItem data = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_notification, null);
        }

        // Установка содержимого уведомления
        ImageView imageView = convertView.findViewById(R.id.notification_image);
        TextView textView = convertView.findViewById(R.id.notification_text);
        TextView datatextView = convertView.findViewById(R.id.notification_textdata);

        imageView.setImageResource(data.getDrawableResource());

        // Если содержимое текста уведомления больше, чем заверено, скрывает остальной текст.
        if (data.getText().length() <= 192) {
            textView.setText(data.getText());
        } else {
            textView.setText(data.getText().substring(0, 190) + "...");
        }

        datatextView.setText(data.getDate());

        return convertView;
    }
}
