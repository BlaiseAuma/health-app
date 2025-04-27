package com.example.trial3;

import android.provider.BaseColumns;

public class MyDatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private MyDatabaseContract() {}

    // Define constants for the table name and column names.
    public static class MyTable implements BaseColumns {
        public static final String TABLE_NAME = "my_table";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        // Add more columns as needed
    }

    // Define SQL queries for creating and deleting the table.
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + MyTable.TABLE_NAME + " (" +
                    MyTable._ID + " INTEGER PRIMARY KEY," +
                    MyTable.COLUMN_NAME_TITLE + " TEXT," +
                    MyTable.COLUMN_NAME_DESCRIPTION + " TEXT)";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + MyTable.TABLE_NAME;
}
