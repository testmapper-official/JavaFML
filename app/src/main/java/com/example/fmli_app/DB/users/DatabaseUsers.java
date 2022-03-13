package com.example.fmli_app.DB.users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class DatabaseUsers {
    public static final String TABLE_NAME = "users";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_ABOUT = "about_me";
    public static final String COLUMN_AVATAR = "avatar_url";
    public static final String COLUMN_BANNER = "banner_url";
    public static final String COLUMN_PERMISSION = "permission";
    public static final String COLUMN_DATE = "creation_date";
    private final SQLiteDatabase dataBase;

    public DatabaseUsers(Context context) {
        UserOpenHelper openHelper = new UserOpenHelper(context);
        dataBase = openHelper.getWritableDatabase();
    }

    // Добавление элемента в таблицу базы данных
    public long insert(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseUsers.COLUMN_LOGIN, user.getLogin());
        contentValues.put(DatabaseUsers.COLUMN_PASSWORD, user.getPassword());
        contentValues.put(DatabaseUsers.COLUMN_EMAIL, user.getEmail());
        contentValues.put(DatabaseUsers.COLUMN_NUMBER, user.getNumber());
        contentValues.put(DatabaseUsers.COLUMN_BIRTHDAY, user.getBirthday());
        contentValues.put(DatabaseUsers.COLUMN_ABOUT, user.getAbout_me());
        contentValues.put(DatabaseUsers.COLUMN_AVATAR, user.getAvatar_url());
        contentValues.put(DatabaseUsers.COLUMN_BANNER, user.getBanner_url());
        contentValues.put(DatabaseUsers.COLUMN_PERMISSION, user.getPermission());
        contentValues.put(DatabaseUsers.COLUMN_DATE, user.getCreation_date());
        return dataBase.insert(DatabaseUsers.TABLE_NAME, null, contentValues);
    }

    // Очищение таблицы базы данных
    protected void clearDatabase() {
        dataBase.delete(DatabaseUsers.TABLE_NAME, null, null);
        String clearDBQuery = "DELETE FROM " + DatabaseUsers.TABLE_NAME;
        dataBase.execSQL(clearDBQuery);
    }

    // Взять пользователя из таблицы базы данных
    public User selectUser(String login, String password) {
        Cursor cursor = dataBase.rawQuery("SELECT *" +
                " FROM " + DatabaseUsers.TABLE_NAME +
                " WHERE (" + DatabaseUsers.COLUMN_LOGIN + "=" + '"' + login + '"' +
                " AND " + DatabaseUsers.COLUMN_PASSWORD + "=" + '"' + password + "\")",
                null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_ID));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_EMAIL));
            String number = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_NUMBER));
            String birthday = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_BIRTHDAY));
            String about_me = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_ABOUT));
            String avatar_url = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_AVATAR));
            String banner_url = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_BANNER));
            String creation_date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_DATE));
            int permission = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_PERMISSION));
            return new User(id, login, password, email, number, about_me, avatar_url, banner_url, creation_date,
                    birthday, permission);
        }
        return null;
    }

    // Взять всех пользователей из таблицы базы данных
    public ArrayList<User> selectAll() {
        Cursor cursor = dataBase.query(DatabaseUsers.TABLE_NAME, null, null,
                null, null, null, null);
        ArrayList<User> userItems = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_ID));
                String login = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_LOGIN));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_PASSWORD));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_EMAIL));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_NUMBER));
                String birthday = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_BIRTHDAY));
                String about_me = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_ABOUT));
                String avatar_url = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_AVATAR));
                String banner_url = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_BANNER));
                String creation_date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_DATE));
                int permission = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseUsers.COLUMN_PERMISSION));
                userItems.add(new User(id, login, password, email, number, about_me, avatar_url, banner_url,
                        creation_date, birthday, permission));
            } while (cursor.moveToNext());
        }

        return userItems;
    }

}
