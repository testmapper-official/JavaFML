package com.example.fmli_app.Fragment.Service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fmli_app.R;

import java.util.ArrayList;

public class NotesAdapter extends ArrayAdapter<Notes> {

    public NotesAdapter(Context context, ArrayList<Notes> data) {
        super(context, R.layout.item_post, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Notes data = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, null);
        }

        TextView head = convertView.findViewById(R.id.post_head);
        TextView body = convertView.findViewById(R.id.post_body);

        String text_body = data.getBody();
        String zip_body = text_body.substring(0, Math.min(20, text_body.length())) + ((20 > text_body.length()) ? "":"...");

        //Заполнение форма
        head.setText(data.getHead());
        body.setText(zip_body);

        return convertView;
    }
}
