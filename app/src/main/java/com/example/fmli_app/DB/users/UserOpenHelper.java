package com.example.fmli_app.DB.users;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fmli_app.DB.DBInformation;
import com.example.fmli_app.DB.notifications.DatabaseNotification;

public class UserOpenHelper extends SQLiteOpenHelper {

    public UserOpenHelper(Context context) {
        super(context, DBInformation.DATABASE_NAME, null, DBInformation.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + DatabaseUsers.TABLE_NAME + " (" +
                DatabaseUsers.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseUsers.COLUMN_LOGIN + " TEXT NOT NULL UNIQUE, " +
                DatabaseUsers.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                DatabaseUsers.COLUMN_EMAIL + " TEXT NOT NULL, " +
                DatabaseUsers.COLUMN_NUMBER + " TEXT, " +
                DatabaseUsers.COLUMN_BIRTHDAY + " DATE, " +
                DatabaseUsers.COLUMN_ABOUT + " TEXT, " +
                DatabaseUsers.COLUMN_AVATAR + " TEXT, " +
                DatabaseUsers.COLUMN_BANNER + " TEXT, " +
                DatabaseUsers.COLUMN_PERMISSION + " INTEGER, " +
                DatabaseUsers.COLUMN_DATE + " DATE);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseUsers.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}