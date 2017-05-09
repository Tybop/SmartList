package com.csci448.tcranor.smartlist;

/**
 * Created by timot on 3/3/2017.
 */
public class NoteDbSchema {
    /**
     * The type Note table.
     */
    public static final class NoteTable {
        /**
         * The constant NAME.
         */
        public static final String NAME = "notes";

        /**
         * The type Cols.
         */
        public static final class Cols {
            /**
             * The constant UUID.
             */
            public static final String UUID = "uuid";
            /**
             * The constant TITLE.
             */
            public static final String TITLE = "title";
            /**
             * The constant DUEDATE.
             */
            public static final String DUEDATE = "dueDate";
            /**
             * The constant SOLVED.
             */
            public static final String SOLVED = "finished";
            /**
             * The constant PRIORITY.
             */
            public static final String PRIORITY = "priority";
            /**
             * The constant EDITDATE.
             */
            public static final String EDITDATE = "editDate";
            /**
             * The constant DETAILS.
             */
            public static final String DETAILS = "details";
            /**
             * The constant TYPE.
             */
            public static final String TYPE = "type";
        }
    }
}
