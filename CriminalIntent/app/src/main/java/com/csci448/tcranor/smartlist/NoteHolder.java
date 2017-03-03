package com.csci448.tcranor.smartlist;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tyler's PC on 2/15/2017.
 */

public class NoteHolder {
    private static NoteHolder sNoteHolder;
    private List<Crime> mCrimes;

    public static NoteHolder get(Context context){
        if (sNoteHolder == null){
            sNoteHolder = new NoteHolder(context);
        }
        return sNoteHolder;
    }

    private NoteHolder(Context context){
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setCompleted(i%2 == 0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime c: mCrimes){
            if (c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }
}
