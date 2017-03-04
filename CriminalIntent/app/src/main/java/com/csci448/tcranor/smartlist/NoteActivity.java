package com.csci448.tcranor.smartlist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class NoteActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.csci448.tcranor.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeID){
        Intent intent = new Intent(packageContext, NoteActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeID);
        return intent;

    }

    @Override
    protected Fragment createFragment(){
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return NoteFragment.newInstance(crimeId);

    }
}
