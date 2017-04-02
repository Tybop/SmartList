package com.csci448.tcranor.smartlist;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tyler's PC on 2/15/2017.
 */

public class NoteHolder {
    private static NoteHolder sNoteHolder;
    private List<Note> mNotes;

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
        mNotes = new ArrayList<>();

        Note note = new Note();
        note.setTitle("Example");
        note.setCompleted(false);
        note.setDetails("An example note.");
        note.setDueDate(new Date());
        note.setPriority(5);
        note.setGroup("General");
        mNotes.add(note);

    }

    public void sortByDate() {
        Collections.sort(mNotes, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return n1.getDueDate().compareTo(n2.getDueDate());
            }

        });
    }

    public void sortByPriority() {
        Collections.sort(mNotes, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return n1.getPriority() - n2.getPriority();
            }

        });
    }

    public void sortByGroup() {
        Collections.sort(mNotes, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return n1.getGroup().compareTo(n2.getGroup());
            }

        });
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
