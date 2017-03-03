package com.csci448.tcranor.smartlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tyler's PC on 2/15/2017.
 */

public class NoteListFragment extends Fragment {

    private RecyclerView mNoteRecyclerView;
    private NoteAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        mNoteRecyclerView = (RecyclerView) view.findViewById(R.id.note_recycler_view);
        mNoteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        com.csci448.tcranor.smartlist.NoteHolder noteHolder = com.csci448.tcranor.smartlist.NoteHolder.get(getActivity());
        List<Note> notes = noteHolder.getNotes();

        if (mAdapter == null) {
            mAdapter = new NoteAdapter(notes);
            mNoteRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Note mNote;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public NoteHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_task_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_task_details_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_task_completed_check_box);

        }

        public void bindNote(Note note){
            mNote = note;
            mTitleTextView.setText(mNote.getTitle());
            mDateTextView.setText(mNote.getDetails().toString());
            mSolvedCheckBox.setChecked(mNote.isCompleted());
        }

        @Override
        public void onClick(View v){
            Intent intent = NotePagerActivity.newIntent(getActivity(), mNote.getId());
            startActivity(intent);
        }

    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder>{
        private List<Note> mNotes;

        public NoteAdapter(List<Note> notes){
            mNotes = notes;
        }

        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_note, parent, false);
            return new NoteHolder(view);
        }

        @Override
        public void onBindViewHolder(NoteHolder holder, int position){
            Note note = mNotes.get(position);
            holder.bindNote(note);
        }

        @Override
        public int getItemCount(){
            return mNotes.size();
        }


    }

}
