package com.example.fmli_app.Fragment.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBNotes {

    protected static final String DATABASE_NAME = "dbnotes.db";
    protected static final int DATABASE_VERSION = 1;
    protected static final String TABLE_NAME = "notes";

    protected static final String COLUMN_ID = "id";
    protected static final String COLUMN_HEAD = "head";
    protected static final String COLUMN_BODY = "body";

    private SQLiteDatabase mDataBase;

    public DBNotes(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(String head, String body) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_HEAD, head);
        cv.put(COLUMN_BODY, body);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Notes notes) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_HEAD, notes.getHead());
        cv.put(COLUMN_BODY, notes.getBody());
        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(notes.getId())});
    }

    public void deleteAll() {
        mDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Notes select(long id) {
        Cursor cur = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        cur.moveToFirst();
        String head = cur.getString(cur.getColumnIndexOrThrow(COLUMN_HEAD));
        String body = cur.getString(cur.getColumnIndexOrThrow(COLUMN_BODY));
        return new Notes(id, head, body);
    }

    public long getNotesId(String head, String body) {
        Cursor cur = mDataBase.query(TABLE_NAME, null, COLUMN_HEAD + " = ? AND " + COLUMN_BODY + " = ?", new String[]{String.valueOf(head), String.valueOf(body)}, null, null, null);

        cur.moveToFirst();
        long id = cur.getLong(cur.getColumnIndexOrThrow(COLUMN_ID));
        return id;
    }

    public ArrayList<Notes> selectAll() {
        Cursor cur = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Notes> arr = new ArrayList<>();
        cur.moveToFirst();
        if (!cur.isAfterLast()) {
            do {
                long id = cur.getLong(cur.getColumnIndexOrThrow(COLUMN_ID));
                String head = cur.getString(cur.getColumnIndexOrThrow(COLUMN_HEAD));
                String body = cur.getString(cur.getColumnIndexOrThrow(COLUMN_BODY));
                arr.add(new Notes(id, head, body));
            } while (cur.moveToNext());
        }
        return arr;
    }

}
