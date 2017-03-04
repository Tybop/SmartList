package com.csci448.tcranor.smartlist.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.csci448.tcranor.smartlist.Note;

import java.util.Date;
import java.util.UUID;

/**
 * Created by timot on 3/3/2017.
 */

public class NoteCursorWrapper extends CursorWrapper {
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote() {
        String uuidString = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.UUID));
        String title = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.TITLE));
        long date = getLong(getColumnIndex(NoteDbSchema.NoteTable.Cols.DATE));
        int isCompleted = getInt(getColumnIndex(NoteDbSchema.NoteTable.Cols.SOLVED));

        Note note = new Note(UUID.fromString(uuidString));
        note.setTitle(title);
        note.setDateEdited(new Date(date));
        note.setCompleted(isCompleted != 0);

        return note;
    }

}
