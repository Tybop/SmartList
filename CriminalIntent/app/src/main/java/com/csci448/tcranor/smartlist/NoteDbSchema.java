package com.csci448.tcranor.smartlist;

import java.util.Date;

/**
 * Created by timot on 3/3/2017.
 */

public class NoteDbSchema {
    public static final class NoteTable {
        public static final String NAME = "notes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DUEDATE = "due date";
            public static final String SOLVED = "finished";
            public static final String PRIORITY = "priority";
            public static final String EDITDATE = "edit date";
            public static final String DETAILS = "details";
            public static final String GROUP = "group";
        }
    }
}
