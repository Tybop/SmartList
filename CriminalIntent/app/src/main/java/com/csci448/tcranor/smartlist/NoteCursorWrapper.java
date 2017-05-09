package com.csci448.tcranor.smartlist;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.csci448.tcranor.smartlist.NoteDbSchema.NoteTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by timot on 3/3/2017.
 */
public class NoteCursorWrapper extends CursorWrapper {
    /**
     * Instantiates a new Note cursor wrapper.
     *
     * @param cursor the cursor
     */
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    /**
     * Gets note.
     *
     * @return the note
     */
    public Note getNote() {
        String uuidString = getString(getColumnIndex(NoteTable.Cols.UUID));
        String title = getString(getColumnIndex(NoteTable.Cols.TITLE));
        long duedate = getLong(getColumnIndex(NoteTable.Cols.DUEDATE));
        int isCompleted = getInt(getColumnIndex(NoteTable.Cols.SOLVED));
        int priority = getInt(getColumnIndex(NoteTable.Cols.PRIORITY));
        long editdate = getLong(getColumnIndex(NoteTable.Cols.EDITDATE));
        String detail = getString(getColumnIndex(NoteTable.Cols.DETAILS));
        String type = getString(getColumnIndex(NoteTable.Cols.TYPE));

        Note note = new Note(UUID.fromString(uuidString));
        note.setTitle(title);
        note.setDateEdited(new Date(editdate));
        note.setCompleted(isCompleted != 0);
        note.setDueDate(new Date(duedate));
        note.setDetails(detail);
        note.setGroup(type);
        note.setPriority(priority);

        return note;
    }

}
