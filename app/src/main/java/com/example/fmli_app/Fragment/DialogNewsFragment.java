package com.example.fmli_app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.fmli_app.DB.news.NewsItem;
import com.example.fmli_app.R;
import com.example.fmli_app.Simple;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class DialogNewsFragment extends DialogFragment {
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Подключение к базе данных
        mDatabase = FirebaseDatabase.getInstance().getReference(NewsItem.key);
        mAuth = FirebaseAuth.getInstance();
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        return new AlertDialog.Builder(requireActivity())
                .setCancelable(true)
                .setView(inflater.inflate(R.layout.dialog_add_news, null))
                .setPositiveButton(R.string.create, (dialog, id) -> {
                    EditText newsTitle = getDialog().findViewById(R.id.news_title);
                    EditText newsContent = getDialog().findViewById(R.id.news_content);
                    //EditText newsUrl = getDialog().findViewById(R.id.news_url);
                    Date now = new Date(System.currentTimeMillis());
                    String title = newsTitle.getText().toString();
                    String text = newsContent.getText().toString();
                    String url = "";//newsUrl.getText().toString();
                    String nid = Simple.Nid(now.getTime());
                    NewsItem newsItem = new NewsItem(nid, mAuth.getUid(), url, now.toString(), text, title);
                    mDatabase.push().setValue(newsItem);

                    Toast.makeText(getContext(), "Вы успешно опубликовали статью", Toast.LENGTH_LONG).show();
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                        DialogNewsFragment.this.getDialog().cancel();
                })
                .create();
    }
}
