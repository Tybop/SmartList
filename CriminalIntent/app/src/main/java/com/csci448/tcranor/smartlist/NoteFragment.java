package com.csci448.tcranor.smartlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by Tyler's PC on 2/8/2017.
 */

public class NoteFragment extends Fragment {

    private static final String ARG_NOTE_ID = "note_id";
    private Note mNote;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSovledCheckBox;

    public static NoteFragment newInstance(UUID noteId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE_ID, noteId);

        NoteFragment fragment = new NoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID noteId = (UUID) getArguments().getSerializable(ARG_NOTE_ID);
        mNote = NoteHolder.get(getActivity()).getNote(noteId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_note, container, false);

        mTitleField = (EditText)v.findViewById(R.id.note_title);
        mTitleField.setText(mNote.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Left Blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Also Blank
            }
        });

        mDateButton = (Button)v.findViewById(R.id.note_details);
        mDateButton.setText(mNote.getDetails().toString());
        mDateButton.setEnabled(false);

        mSovledCheckBox = (CheckBox)v.findViewById(R.id.note_completed);
        mSovledCheckBox.setChecked(mNote.isCompleted());
        mSovledCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mNote.setCompleted(isChecked);
            }
        });

        return v;
    }


}
