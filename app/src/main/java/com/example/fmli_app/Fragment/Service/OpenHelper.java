package com.example.fmli_app.Fragment.Service;

import static com.example.fmli_app.Fragment.Service.DBNotes.COLUMN_BODY;
import static com.example.fmli_app.Fragment.Service.DBNotes.COLUMN_HEAD;
import static com.example.fmli_app.Fragment.Service.DBNotes.COLUMN_ID;
import static com.example.fmli_app.Fragment.Service.DBNotes.DATABASE_NAME;
import static com.example.fmli_app.Fragment.Service.DBNotes.DATABASE_VERSION;
import static com.example.fmli_app.Fragment.Service.DBNotes.TABLE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(" + COLUMN_ID
                + " integer primary key," + COLUMN_HEAD + " text," + COLUMN_BODY + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
