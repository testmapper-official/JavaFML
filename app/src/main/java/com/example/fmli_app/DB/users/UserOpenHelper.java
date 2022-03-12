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
//        public static final String COLUMN_ID = "id";
//        public static final String COLUMN_LOGIN = "login";
//        public static final String COLUMN_PASSWORD = "password";
//        public static final String COLUMN_BIRTHDAY = "birthday";
//        public static final String COLUMN_ABOUT = "about_me";
//        public static final String COLUMN_AVATAR = "avatar_url";
//        public static final String COLUMN_BANNER = "banner_url";
//        public static final String COLUMN_PERMISSION = "permission";
//        public static final String COLUMN_DATE = "creation_date";
        String query = "CREATE TABLE " + DatabaseNotification.TABLE_NAME + " (" +
                DatabaseUsers.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseUsers.COLUMN_LOGIN + " TEXT, " +
                DatabaseUsers.COLUMN_PASSWORD + " TEXT, " +
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