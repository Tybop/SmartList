package com.csci448.tcranor.smartlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Tyler's PC on 2/8/2017.
 */

public class NoteFragment extends Fragment {

    private static final String ARG_NOTE_ID = "note_id";
    private Note mNote;
    private EditText mTitleField;
    private EditText mDetailsField;
    private EditText mGroupField;
    private DatePicker mDueDate;
    private Button mSubmitButton;
    private Spinner mPrioritySpinner;



    public static NoteFragment newInstance(UUID noteId) {
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
        mNote = NoteTemplate.get(getActivity()).getNote(noteId);
    }

/*    @Override
    public void onPause() {
        super.onPause();

        NoteTemplate.get(getActivity()).updateNote(mNote);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note, container, false);

        mTitleField = (EditText) v.findViewById(R.id.note_title);
        mTitleField.setText(mNote.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Left Blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setTitle(s.toString());
                mNote.setDateEdited(new Date());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Also Blank
            }
        });

        mDetailsField = (EditText) v.findViewById(R.id.note_details);
        mDetailsField.setText(mNote.getDetails().toString());
        mDetailsField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Left Blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setDetails(s.toString());
                mNote.setDateEdited(new Date());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Also Blank
            }
        });

        mGroupField = (EditText) v.findViewById(R.id.note_group);
        mGroupField.setText(mNote.getGroup().toString());
        mGroupField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Left Blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setGroup(s.toString());
                mNote.setDateEdited(new Date());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Also Blank
            }
        });

        mDueDate = (DatePicker) v.findViewById(R.id.due_date_picker);

        mSubmitButton = (Button) v.findViewById(R.id.date_submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Date tmpDate = new Date(mDueDate.getYear(), mDueDate.getMonth(), mDueDate.getDayOfMonth());
                mNote.setDueDate(tmpDate);
            }
        });


        mPrioritySpinner = (Spinner) v.findViewById(R.id.priority_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPrioritySpinner.setAdapter(adapter);
        mNote.setPriority((int)mPrioritySpinner.getSelectedItem());

        return v;
    }


}
