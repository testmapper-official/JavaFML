package com.example.fmli_app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fmli_app.R;

public class testFragment extends Fragment {
    Button btn;
    TextView textview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        btn = view.findViewById(R.id.step);
        textview = view.findViewById(R.id.stepview);

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            textview.setText(textview.getText().toString() + "+");
        }
    });

        return view;
    }
}
