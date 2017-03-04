package com.csci448.tcranor.smartlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.csci448.tcranor.smartlist.database.NoteBaseHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tyler's PC on 2/15/2017.
 */

public class NoteHolder {
    private static NoteHolder sNoteHolder;
    private List<Note> mNotes;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static NoteHolder get(Context context) {
        if (sNoteHolder == null) {
            sNoteHolder = new NoteHolder(context);
        }
        return sNoteHolder;
    }


    public void addNote(Note note) {
        mNotes.add(note);
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
        return mNotes;
    }

    public Note getNote(UUID id) {
        for (Note c : mNotes) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }
}
