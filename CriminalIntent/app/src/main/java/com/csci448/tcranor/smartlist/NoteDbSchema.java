package com.csci448.tcranor.smartlist;

/**
 * Created by timot on 3/3/2017.
 */

public class NoteDbSchema {
    public static final class NoteTable {
        public static final String NAME = "notes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DUEDATE = "dueDate";
            public static final String SOLVED = "finished";
            public static final String PRIORITY = "priority";
            public static final String EDITDATE = "editDate";
            public static final String DETAILS = "details";
            public static final String TYPE = "type";
        }
    }
}
