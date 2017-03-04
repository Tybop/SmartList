package com.csci448.tcranor.smartlist.database;

/**
 * Created by timot on 3/3/2017.
 */

public class NoteDbSchema {
    public static final class NoteTable {
        public static final String NAME = "crimes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }
}
