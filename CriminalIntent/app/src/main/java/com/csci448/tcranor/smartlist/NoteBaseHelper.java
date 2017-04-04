package com.csci448.tcranor.smartlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.csci448.tcranor.smartlist.NoteDbSchema.NoteTable;

/**
 * Created by timot on 3/3/2017.
 */

public class NoteBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "noteBase.db";

    public NoteBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NoteTable.NAME + "(" + " _id integer primary key autoincrement, "
                + NoteTable.Cols.UUID + ", "
                + NoteTable.Cols.TITLE + ", "
                + NoteTable.Cols.DUEDATE + ", "
                + NoteTable.Cols.SOLVED + ", "
                + NoteTable.Cols.PRIORITY + ", "
                + NoteTable.Cols.EDITDATE + ", "
                + NoteTable.Cols.DETAILS + ", "
                + NoteTable.Cols.TYPE +
                ")");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
