package com.example.nas19_journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "entries";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_MOOD = "mood";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private static final String DATABASE_NAME = "journal.db";
    private static final int DATABASE_VERSION = 1;
    private static EntryDatabase instance;

    private EntryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /** create journal entries table if it does not exist. */
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ENTRIES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT NOT NULL, " +
                COLUMN_CONTENT + " TEXT NOT NULL, " +
                COLUMN_MOOD + " INTEGER NOT NULL, " +
                COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_ENTRIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public static EntryDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            instance = new EntryDatabase(context);
            return instance;
        }
    }

    public Cursor selectAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM entries", null);
        return c;
    }

    public void insert(JournalEntry entry) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, entry.getTitle());
        cv.put(COLUMN_CONTENT, entry.getContent());
        cv.put(COLUMN_MOOD, entry.getMood());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, cv);
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_ID+"="+id);
    }

}
