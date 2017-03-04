package com.csci448.tcranor.smartlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.csci448.tcranor.smartlist.database.NoteBaseHelper;
import com.csci448.tcranor.smartlist.database.NoteCursorWrapper;
import com.csci448.tcranor.smartlist.database.NoteDbSchema.NoteTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tyler's PC on 2/15/2017.
 */

public class NoteHolder {
    private static NoteHolder sNoteHolder;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private List<Note> mNotes;

    public static NoteHolder get(Context context) {
        if (sNoteHolder == null) {
            sNoteHolder = new NoteHolder(context);
        }
        return sNoteHolder;
    }


    public void addNote(Note note) {
        ContentValues values = getContentValues(note);
        mDatabase.insert(NoteTable.NAME, null, values);
    }

    private NoteHolder(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NoteBaseHelper(mContext).getWritableDatabase();
        mNotes = new ArrayList<>();
        Note note = new Note();
        note.setTitle("Example");
        note.setCompleted(false);
        note.setDetails("An example note.");
        note.setDueDate(new Date());
        note.setUrgency(5);
        note.setGroup("General");
        note.setDateEdited(new Date());
        mNotes.add(note);

    }

    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();

        NoteCursorWrapper cursor = queryNotes(null, null);

        try {
            while (!cursor.isAfterLast()) {
                notes.add(cursor.getNote());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();

        }
        return notes;
    }


    public void updateNote(Note note) {
        String uuidString = note.getId().toString();
        ContentValues values = getContentValues(note);

        mDatabase.update(NoteTable.NAME,
                values,
                NoteTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public Note getNote(UUID id) {

        NoteCursorWrapper cursor = queryNotes(
                NoteTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getNote();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteTable.Cols.UUID, note.getId().toString());
        values.put(NoteTable.Cols.TITLE, note.getTitle());
        values.put(NoteTable.Cols.DATE, note.getDateEdited().getTime());
        values.put(NoteTable.Cols.SOLVED, note.isCompleted() ? 1 : 0);
        return values;
    }

    private NoteCursorWrapper queryNotes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                NoteTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new NoteCursorWrapper(cursor);
    }
}
