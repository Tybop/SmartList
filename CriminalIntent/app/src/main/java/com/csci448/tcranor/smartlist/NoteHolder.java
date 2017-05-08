package com.csci448.tcranor.smartlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.csci448.tcranor.smartlist.NoteDbSchema.NoteTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tyler's PC on 2/15/2017.
 */

public class NoteHolder {
    private static NoteHolder sNoteHolder;
    private Context mContext;
    private SQLiteDatabase mDatabase;

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

    public NoteHolder(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NoteBaseHelper(mContext).getWritableDatabase();

    }

    public List<Note> sortByDate() {
        List<Note> tmp = getNotes();
        Collections.sort(tmp, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return n1.getDueDate().compareTo(n2.getDueDate());
            }

        });
        return tmp;
    }

    public List<Note> sortByPriority() {
        List<Note> tmp = getNotes();
        Collections.sort(tmp, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return n1.getPriority() - n2.getPriority();
            }

        });
        return tmp;
    }

    public List<Note> sortByGroup() {
        List<Note> tmp = getNotes();
        Collections.sort(tmp, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return n1.getGroup().compareTo(n2.getGroup());
            }

        });
        return tmp;
    }

    public List<Note> sortByDateEdited() {
        List<Note> tmp = getNotes();
        Collections.sort(tmp, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return n1.getDateEdited().compareTo(n2.getDateEdited());
            }

        });
        return tmp;
    }

    public List<Note> sortByTitle() {
        List<Note> tmp = getNotes();
        Collections.sort(tmp, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return n1.getTitle().compareTo(n2.getTitle());
            }

        });
        return tmp;
    }

    public List<Note> sortBySolved() {
        List<Note> tmp = getNotes();
        Collections.sort(tmp, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return Boolean.compare(n1.isCompleted(), n2.isCompleted());
            }

        });
        return tmp;
    }

    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();

        NoteCursorWrapper cursor = queryNotes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                notes.add(cursor.getNote());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return notes;
    }

    public Note getNote(UUID id) {
        NoteCursorWrapper cursor = queryNotes(NoteTable.Cols.UUID + " = ?", new String[]{id.toString()});

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

    public void updateCrime(Note note) {
        String uuidString = note.getId().toString();
        ContentValues values = getContentValues(note);

        mDatabase.update(NoteTable.NAME, values, NoteTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    private static ContentValues getContentValues(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteTable.Cols.UUID, note.getId().toString());
        values.put(NoteTable.Cols.TITLE, note.getTitle());
        values.put(NoteTable.Cols.DUEDATE, note.getDueDate().getTime());
        values.put(NoteTable.Cols.SOLVED, note.isCompleted() ? 1 : 0);
        values.put(NoteTable.Cols.PRIORITY, note.getPriority());
        values.put(NoteTable.Cols.EDITDATE, note.getDateEdited().getTime());
        values.put(NoteTable.Cols.DETAILS, note.getDetails());
        values.put(NoteTable.Cols.TYPE, note.getGroup());

        return values;
    }

    private NoteCursorWrapper queryNotes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                NoteTable.NAME, null, whereClause, whereArgs, null, null, null
        );

        return new NoteCursorWrapper(cursor);
    }

}
