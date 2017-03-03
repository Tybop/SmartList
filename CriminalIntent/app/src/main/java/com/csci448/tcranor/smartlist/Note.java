package com.csci448.tcranor.smartlist;

import java.util.UUID;

/**
 * Created by Tyler's PC on 2/8/2017.
 */

public class Note {

    private UUID mId;
    private String mTitle;
    private String mDetails;
    private boolean mCompleted;

    public Note(){
        mId = UUID.randomUUID();
        mDetails = new String();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String details) {
        mDetails = details;
    }
}
