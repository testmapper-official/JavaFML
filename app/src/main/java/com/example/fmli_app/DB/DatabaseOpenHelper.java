package com.example.fmli_app.DB;

import static com.example.fmli_app.DB.Database.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "fml_9943.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createNotificationsTable(sqLiteDatabase);
        createUsersTable(sqLiteDatabase);
    }

    public void createNotificationsTable(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME_NOTIFICATIONS + " (" +
                TABLE_NAME_NOTIFICATIONS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_NAME_NOTIFICATIONS_COLUMN_USER_ID + " INTEGER, " +
                TABLE_NAME_NOTIFICATIONS_COLUMN_TYPE + " INTEGER, " +
                TABLE_NAME_NOTIFICATIONS_COLUMN_TEXT + " TEXT, " +
                TABLE_NAME_NOTIFICATIONS_COLUMN_DATE + " DATE);";
        sqLiteDatabase.execSQL(query);
    }

    public void createUsersTable(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME_USERS + " (" +
                TABLE_NAME_USERS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_NAME_USERS_COLUMN_PASSWORD + " TEXT NOT NULL, " +
                TABLE_NAME_USERS_COLUMN_EMAIL + " TEXT, " +
                TABLE_NAME_USERS_COLUMN_NUMBER + " TEXT, " +
                TABLE_NAME_USERS_COLUMN_BIRTHDAY + " DATE, " +
                TABLE_NAME_USERS_COLUMN_ABOUT + " TEXT, " +
                TABLE_NAME_USERS_COLUMN_AVATAR + " TEXT, " +
                TABLE_NAME_USERS_COLUMN_BANNER + " TEXT, " +
                TABLE_NAME_USERS_COLUMN_PERMISSION + " INTEGER, " +
                TABLE_NAME_USERS_COLUMN_DATE + " DATE);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_NOTIFICATIONS);
        onCreate(sqLiteDatabase);
    }

}