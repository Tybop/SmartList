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
    private int mPriority;
    private String mGroup;
    private Date mDateEdited;
    private boolean mCompleted;


    /**
     * Instantiates a new Note.
     */
    public Note() {

        mId = UUID.randomUUID();
        mDetails = "";
        mTitle = "";
        mGroup = "";
        mPriority = 1;
        mDueDate = new Date();
        mDateEdited = new Date();
        mCompleted = false;
    }

    /**
     * Instantiates a new Note.
     *
     * @param uuid the uuid
     */
    public Note(UUID uuid) {
        mId = uuid;
        mDetails = "";
        mTitle = "";
        mGroup = "";
        mPriority = 1;
        mDueDate = new Date();
        mDateEdited = new Date();
        mCompleted = false;

    }

    /**
     * Gets date edited.
     *
     * @return the date edited
     */
    public Date getDateEdited() {
        if (mDateEdited == null)
            return new Date();
        return mDateEdited;
    }

    /**
     * Sets date edited.
     *
     * @param dateEdited the date edited
     */
    public void setDateEdited(Date dateEdited) {
        mDateEdited = dateEdited;
    }

    /**
     * Gets due date.
     *
     * @return the due date
     */
    public Date getDueDate() {
        if (mDueDate == null)
            return new Date();
        return mDueDate;
    }

    /**
     * Sets due date.
     *
     * @param dueDate the due date
     */
    public void setDueDate(Date dueDate) {
        mDueDate = dueDate;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public int getPriority() {
        return mPriority;
    }

    /**
     * Sets priority.
     *
     * @param priority the priority
     */
    public void setPriority(int priority) {
        mPriority = priority;
    }

    /**
     * Gets group.
     *
     * @return the group
     */
    public String getGroup() {
        return mGroup;
    }

    /**
     * Sets group.
     *
     * @param group the group
     */
    public void setGroup(String group) {
        mGroup = group;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public UUID getId() {
        return mId;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * Is completed boolean.
     *
     * @return the boolean
     */
    public boolean isCompleted() {
        return mCompleted;
    }

    /**
     * Sets completed.
     *
     * @param completed the completed
     */
    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    /**
     * Gets details.
     *
     * @return the details
     */
    public String getDetails() {
        return mDetails;
    }

    /**
     * Sets details.
     *
     * @param details the details
     */
    public void setDetails(String details) {
        mDetails = details;
    }
}
