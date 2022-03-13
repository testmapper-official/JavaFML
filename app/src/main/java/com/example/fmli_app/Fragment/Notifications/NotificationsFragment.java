package com.example.fmli_app.Fragment.Notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fmli_app.DB.Database;
import com.example.fmli_app.DB.notifications.NotificationAdapter;
import com.example.fmli_app.DB.notifications.NotificationItem;
import com.example.fmli_app.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // Подключение к базе данных
        Database db = new Database(getContext());

//        db.clearDatabase();
//        Date newDate = new Date(System.currentTimeMillis());
//        db.insert(new NotificationItem(1, 1, getContext().getString(R.string.empty_text), newDate.toString()));
//        db.insert(new NotificationItem(1, 2, getContext().getString(R.string.empty_text), newDate.toString()));

        // Инициализаци ListView фрагмента уведомлений
        ArrayList<NotificationItem> data = db.selectAllNotifications();

        ListView notificationView = view.findViewById(R.id.notification_list);
        NotificationAdapter arrayAdapter = new NotificationAdapter(getContext(), data);
        notificationView.setAdapter(arrayAdapter);

        // Выведение системного уведомления при нажатии элемента фрагмента уведомлений
        notificationView.setOnItemClickListener((adapterView, view1, i, l) -> {
            String elementText = adapterView.getAdapter().getItem(i).toString();
            Toast.makeText(getContext(), elementText, Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}