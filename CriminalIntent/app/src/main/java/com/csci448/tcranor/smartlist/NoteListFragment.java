package com.csci448.tcranor.smartlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_note_list, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_note:
                Note note = new Note();
                com.csci448.tcranor.smartlist.NoteHolder.get(getActivity()).addNote(note);
                Intent intent = NotePagerActivity.newIntent(getActivity(), note.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_sort_by_date:
                List<Note> dateNotes = com.csci448.tcranor.smartlist.NoteHolder.get(getActivity()).sortByDate();
                refreshUI(dateNotes);
                return true;
            case R.id.menu_item_delete_note:
                List<Note> toDeleteNotes= com.csci448.tcranor.smartlist.NoteHolder.get(getActivity()).deleteCheckedNotes();
                refreshUI(toDeleteNotes);
                return true;
            case R.id.menu_item_sort_by_priority:
                List<Note> priorityNotes = com.csci448.tcranor.smartlist.NoteHolder.get(getActivity()).sortByPriority();
                refreshUI(priorityNotes);
                return true;
            case R.id.menu_item_sort_by_group:
                List<Note> groupNotes = com.csci448.tcranor.smartlist.NoteHolder.get(getActivity()).sortByGroup();
                refreshUI(groupNotes);
                return true;
            case R.id.menu_item_sort_by_date_edited:
                List<Note> dateEditedNotes = com.csci448.tcranor.smartlist.NoteHolder.get(getActivity()).sortByDateEdited();
                refreshUI(dateEditedNotes);
                return true;
            case R.id.menu_item_sort_by_solved:
                List<Note> solvedNotes = com.csci448.tcranor.smartlist.NoteHolder.get(getActivity()).sortBySolved();
                refreshUI(solvedNotes);
                return true;
            case R.id.menu_item_sort_by_title:
                List<Note> titleNotes = com.csci448.tcranor.smartlist.NoteHolder.get(getActivity()).sortByTitle();
                refreshUI(titleNotes);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        com.csci448.tcranor.smartlist.NoteHolder noteHolder = com.csci448.tcranor.smartlist.NoteHolder.get(getActivity());
        List<Note> notes = noteHolder.getNotes();

        if (mAdapter == null) {
            mAdapter = new NoteAdapter(notes);
            mNoteRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setNotes(notes);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void refreshUI(List<Note> sortedNotes) {

        if (mAdapter == null) {
            mAdapter = new NoteAdapter(sortedNotes);
            mNoteRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setNotes(sortedNotes);
            mAdapter.notifyDataSetChanged();

        }
    }

    private class NoteHold extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Note mNote;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public NoteHold(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_task_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_task_details_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_task_completed_check_box);

        }

        public void bindNote(Note note) {
            mNote = note;
            mTitleTextView.setText(mNote.getTitle());
            mDateTextView.setText(mNote.getDetails().toString());
            mSolvedCheckBox.setChecked(mNote.isCompleted());
        }

        @Override
        public void onClick(View v) {
            Intent intent = NotePagerActivity.newIntent(getActivity(), mNote.getId());
            startActivity(intent);
        }

    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteHold> {
        private List<Note> mNotes;

        public NoteAdapter(List<Note> notes) {
            mNotes = notes;
        }

        @Override
        public NoteHold onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_note, parent, false);
            return new NoteHold(view);
        }

        @Override
        public void onBindViewHolder(NoteHold holder, int position) {
            Note note = mNotes.get(position);
            holder.bindNote(note);
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }

        public void setNotes(List<Note> notes) {
            mNotes = notes;
        }

    }

}
