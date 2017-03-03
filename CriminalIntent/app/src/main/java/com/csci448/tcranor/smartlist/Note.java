package com.csci448.tcranor.smartlist;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Tyler's PC on 2/8/2017.
 */

public class Note {

    private UUID mId;
    private String mTitle;
    private String mDetails;
    private Date mDueDate;
    private int mUrgency;
    private String mGroup;
    private Date mDateEdited;

    public Date getDateEdited() {
        return mDateEdited;
    }

    public void setDateEdited(Date dateEdited) {
        mDateEdited = dateEdited;
    }

    
    public Date getDueDate() {
        return mDueDate;
    }

    public void setDueDate(Date dueDate) {
        mDueDate = dueDate;
    }

    public int getUrgency() {
        return mUrgency;
    }

    public void setUrgency(int urgency) {
        mUrgency = urgency;
    }

    public String getGroup() {
        return mGroup;
    }

    public void setGroup(String group) {
        mGroup = group;
    }

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
