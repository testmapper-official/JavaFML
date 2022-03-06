package com.example.fmli_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseNotification {
    public static final String TABLE_NAME = "notifications";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_TEXT = "password";
    public static final String COLUMN_DATE = "creation_date";

    private final SQLiteDatabase dataBase;

    public DatabaseNotification(Context context) {
        NotificationOpenHelper openHelper = new NotificationOpenHelper(context);
        dataBase = openHelper.getWritableDatabase();
    }

    // Добавление элемента в таблицу базы данных
    public long insert(NotificationItem notificationItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_ID, notificationItem.getUserId());
        contentValues.put(COLUMN_TYPE, notificationItem.getType());
        contentValues.put(COLUMN_TEXT, notificationItem.getText());
        contentValues.put(COLUMN_DATE, notificationItem.getDate());
        return dataBase.insert(DatabaseNotification.TABLE_NAME, null, contentValues);
    }

    // Очищение таблицы базы данных
    protected void clearDatabase() {
        dataBase.delete(TABLE_NAME,null,null);
        String clearDBQuery = "DELETE FROM " + DatabaseNotification.TABLE_NAME;
        dataBase.execSQL(clearDBQuery);
    }

    public void upgradeDatabase() {

    }

    // Взять все элементы таблицы базы данных
    public ArrayList<NotificationItem> selectAll() {
        Cursor cursor = dataBase.query(TABLE_NAME, null, null,
                null, null, null, null);
        ArrayList<NotificationItem> notificationItems = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
                long userId = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_USER_ID));
                int type = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TYPE));
                String text = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEXT));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE));
                notificationItems.add(new NotificationItem(id, userId, type, text, date));
            } while (cursor.moveToNext());
        }

        return notificationItems;
    }

}
