package com.example.fmli_app.DB.notifications;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fmli_app.DB.users.User;

import java.util.ArrayList;

public class DatabaseNotification {
    public static final String TABLE_NAME = "notifications";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_TEXT = "text_content";
    public static final String COLUMN_DATE = "creation_date";

    private final SQLiteDatabase dataBase;

    public DatabaseNotification(Context context) {
        NotificationOpenHelper openHelper = new NotificationOpenHelper(context);
        dataBase = openHelper.getWritableDatabase();
    }

    // Добавление элемента в таблицу базы данных
    public long insert(NotificationItem notificationItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseNotification.COLUMN_USER_ID, notificationItem.getUserId());
        contentValues.put(DatabaseNotification.COLUMN_TYPE, notificationItem.getType());
        contentValues.put(DatabaseNotification.COLUMN_TEXT, notificationItem.getText());
        contentValues.put(DatabaseNotification.COLUMN_DATE, notificationItem.getDate());
        return dataBase.insert(DatabaseNotification.TABLE_NAME, null, contentValues);
    }

    // Очищение таблицы базы данных
    protected void clearDatabase() {
        dataBase.delete(DatabaseNotification.TABLE_NAME, null, null);
        String clearDBQuery = "DELETE FROM " + DatabaseNotification.TABLE_NAME;
        dataBase.execSQL(clearDBQuery);
    }

    // Взять все пользовательские элементы таблицы базы данных
    public ArrayList<NotificationItem> selectForUser(User user) {
        Cursor cursor = dataBase.rawQuery("SELECT *" +
                " WHERE " + DatabaseNotification.COLUMN_USER_ID + "=" + user.getId() +
                " FROM " + DatabaseNotification.TABLE_NAME, null);
        ArrayList<NotificationItem> notificationItems = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseNotification.COLUMN_ID));
                long userId = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseNotification.COLUMN_USER_ID));
                int type = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseNotification.COLUMN_TYPE));
                String text = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseNotification.COLUMN_TEXT));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseNotification.COLUMN_DATE));
                notificationItems.add(new NotificationItem(id, userId, type, text, date));
            } while (cursor.moveToNext());
        }
        return notificationItems;
    }

    // Взять все элементы таблицы базы данных
    public ArrayList<NotificationItem> selectAll() {
        Cursor cursor = dataBase.query(DatabaseNotification.TABLE_NAME, null, null,
                null, null, null, null);
        ArrayList<NotificationItem> notificationItems = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseNotification.COLUMN_ID));
                long userId = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseNotification.COLUMN_USER_ID));
                int type = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseNotification.COLUMN_TYPE));
                String text = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseNotification.COLUMN_TEXT));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseNotification.COLUMN_DATE));
                notificationItems.add(new NotificationItem(id, userId, type, text, date));
            } while (cursor.moveToNext());
        }

        return notificationItems;
    }

}
