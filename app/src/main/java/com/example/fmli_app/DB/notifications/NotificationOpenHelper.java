package com.example.fmli_app.DB.notifications;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fmli_app.DB.DBInformation;
import com.example.fmli_app.DB.notifications.DatabaseNotification;

public class NotificationOpenHelper extends SQLiteOpenHelper {

    public NotificationOpenHelper(Context context) {
        super(context, DBInformation.DATABASE_NAME, null, DBInformation.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + DatabaseNotification.TABLE_NAME + " (" +
                DatabaseNotification.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseNotification.COLUMN_USER_ID + " INTEGER, " +
                DatabaseNotification.COLUMN_TYPE + " INTEGER, " +
                DatabaseNotification.COLUMN_TEXT + " TEXT, " +
                DatabaseNotification.COLUMN_DATE + " DATE);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseNotification.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}