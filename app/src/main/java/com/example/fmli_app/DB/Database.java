package com.example.fmli_app.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fmli_app.DB.notifications.NotificationItem;
import com.example.fmli_app.DB.users.User;

import java.util.ArrayList;

public class Database {
    // Названия таблиц базы данных
    public static final String TABLE_NAME_NOTIFICATIONS = "notifications";
    public static final String TABLE_NAME_USERS = "users";
    public static final String TABLE_NAME_TAGS = "tags";
    public static final String TABLE_NAME_POSTS = "posts";
    public static final String TABLE_NAME_NEWS = "news";
    // Названия полей таблицы users базы данных
    public static final String TABLE_NAME_USERS_COLUMN_ID = "id";
    public static final String TABLE_NAME_USERS_COLUMN_PASSWORD = "password";
    public static final String TABLE_NAME_USERS_COLUMN_EMAIL = "email";
    public static final String TABLE_NAME_USERS_COLUMN_NUMBER = "number";
    public static final String TABLE_NAME_USERS_COLUMN_BIRTHDAY = "birthday";
    public static final String TABLE_NAME_USERS_COLUMN_ABOUT = "about_me";
    public static final String TABLE_NAME_USERS_COLUMN_AVATAR = "avatar_url";
    public static final String TABLE_NAME_USERS_COLUMN_BANNER = "banner_url";
    public static final String TABLE_NAME_USERS_COLUMN_PERMISSION = "permission";
    public static final String TABLE_NAME_USERS_COLUMN_DATE = "creation_date";
    // Названия полей таблицы notifications базы данных
    public static final String TABLE_NAME_NOTIFICATIONS_COLUMN_ID = "id";
    public static final String TABLE_NAME_NOTIFICATIONS_COLUMN_USER_ID = "user_id";
    public static final String TABLE_NAME_NOTIFICATIONS_COLUMN_TYPE = "type";
    public static final String TABLE_NAME_NOTIFICATIONS_COLUMN_TEXT = "text_content";
    public static final String TABLE_NAME_NOTIFICATIONS_COLUMN_DATE = "creation_date";

    private final SQLiteDatabase dataBase;

    public Database(Context context) {
        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(context);
        dataBase = openHelper.getWritableDatabase();
    }

    // Добавление уведомления NotificationItem в таблицу базы данных
    public long insert(NotificationItem notificationItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_NAME_NOTIFICATIONS_COLUMN_ID, notificationItem.getUserId());
        contentValues.put(TABLE_NAME_NOTIFICATIONS_COLUMN_USER_ID, notificationItem.getType());
        contentValues.put(TABLE_NAME_NOTIFICATIONS_COLUMN_TYPE, notificationItem.getText());
        contentValues.put(TABLE_NAME_NOTIFICATIONS_COLUMN_TEXT, notificationItem.getDate());
        return dataBase.insert(TABLE_NAME_NOTIFICATIONS, null, contentValues);
    }

    // Добавление пользователя User в таблицу базы данных
    public long insert(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_NAME_USERS_COLUMN_PASSWORD, user.getPassword());
        contentValues.put(TABLE_NAME_USERS_COLUMN_EMAIL, user.getEmail());
        contentValues.put(TABLE_NAME_USERS_COLUMN_NUMBER, user.getNumber());
        contentValues.put(TABLE_NAME_USERS_COLUMN_BIRTHDAY, user.getBirthday());
        contentValues.put(TABLE_NAME_USERS_COLUMN_ABOUT, user.getAbout_me());
        contentValues.put(TABLE_NAME_USERS_COLUMN_AVATAR, user.getAvatar_url());
        contentValues.put(TABLE_NAME_USERS_COLUMN_BANNER, user.getBanner_url());
        contentValues.put(TABLE_NAME_USERS_COLUMN_PERMISSION, user.getPermission());
        contentValues.put(TABLE_NAME_USERS_COLUMN_DATE, user.getCreation_date());
        return dataBase.insert(TABLE_NAME_USERS, null, contentValues);
    }

    // Очищение таблицы notifications в базе данных
    protected void deleteNotifications() {
        dataBase.delete(TABLE_NAME_NOTIFICATIONS, null, null);
        String clearDBQuery = "DELETE FROM " + TABLE_NAME_NOTIFICATIONS;
        dataBase.execSQL(clearDBQuery);
    }

    // Очищение таблицы users в базе данных
    protected void deleteUsers() {
        dataBase.delete(TABLE_NAME_USERS, null, null);
        String clearDBQuery = "DELETE FROM " + TABLE_NAME_USERS;
        dataBase.execSQL(clearDBQuery);
    }

    // Взять все пользовательские уведомления NotificationItems из таблицы notifications в базе данных
    public ArrayList<NotificationItem> selectUserNotifications(User user) {
        Cursor cursor = dataBase.rawQuery("SELECT *" +
                        " FROM " + TABLE_NAME_NOTIFICATIONS +
                        " WHERE " + TABLE_NAME_NOTIFICATIONS_COLUMN_USER_ID + "=" + user.getId(),
                null);
        ArrayList<NotificationItem> notificationItems = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(TABLE_NAME_NOTIFICATIONS_COLUMN_ID));
                long userId = user.getId();
                int type = cursor.getInt(cursor.getColumnIndexOrThrow(TABLE_NAME_NOTIFICATIONS_COLUMN_TYPE));
                String text = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_NOTIFICATIONS_COLUMN_TEXT));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_NOTIFICATIONS_COLUMN_DATE));
                notificationItems.add(new NotificationItem(id, userId, type, text, date));
            } while (cursor.moveToNext());
        }
        return notificationItems;
    }

    // Взять все уведомления NotificationItems из таблицы notifications в базе данных
    public ArrayList<NotificationItem> selectAllNotifications() {
        Cursor cursor = dataBase.query(TABLE_NAME_NOTIFICATIONS, null, null,
                null, null, null, null);
        ArrayList<NotificationItem> notificationItems = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(TABLE_NAME_NOTIFICATIONS_COLUMN_ID));
                long userId = cursor.getLong(cursor.getColumnIndexOrThrow(TABLE_NAME_NOTIFICATIONS_COLUMN_USER_ID));
                int type = cursor.getInt(cursor.getColumnIndexOrThrow(TABLE_NAME_NOTIFICATIONS_COLUMN_TYPE));
                String text = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_NOTIFICATIONS_COLUMN_TEXT));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_NOTIFICATIONS_COLUMN_DATE));
                notificationItems.add(new NotificationItem(id, userId, type, text, date));
            } while (cursor.moveToNext());
        }

        return notificationItems;
    }

    // Взять пользователя User из таблицы users в базе данных
    public User selectUser(String login, String password) {
        Cursor cursor = dataBase.rawQuery("SELECT *" +
                        " FROM " + TABLE_NAME_USERS +
                        " WHERE ((" + TABLE_NAME_USERS_COLUMN_EMAIL + "=" + '"' + login + '"' +
                        " OR " + TABLE_NAME_USERS_COLUMN_NUMBER + "=" + '"' + login + "\")" +
                        " AND " + TABLE_NAME_USERS_COLUMN_PASSWORD + "=" + '"' + password + "\")",
                null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_ID));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_EMAIL));
            String number = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_NUMBER));
            String birthday = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_BIRTHDAY));
            String about_me = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_ABOUT));
            String avatar_url = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_AVATAR));
            String banner_url = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_BANNER));
            String creation_date = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_DATE));
            int permission = cursor.getInt(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_PERMISSION));
            return new User(id, password, email, number, about_me, avatar_url, banner_url, creation_date,
                    birthday, permission);
        }
        return null;
    }

    // Взять всех пользователей Users из таблицы users в базе данных
    public ArrayList<User> selectAllUsers() {
        Cursor cursor = dataBase.query(TABLE_NAME_USERS, null, null,
                null, null, null, null);
        ArrayList<User> userItems = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_ID));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_PASSWORD));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_EMAIL));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_NUMBER));
                String birthday = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_BIRTHDAY));
                String about_me = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_ABOUT));
                String avatar_url = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_AVATAR));
                String banner_url = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_BANNER));
                String creation_date = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_DATE));
                int permission = cursor.getInt(cursor.getColumnIndexOrThrow(TABLE_NAME_USERS_COLUMN_PERMISSION));
                userItems.add(new User(id, password, email, number, about_me, avatar_url, banner_url,
                        creation_date, birthday, permission));
            } while (cursor.moveToNext());
        }

        return userItems;
    }

}
