package com.example.trial3;

public class DatabaseContract {

    // Define constants for table names
    public static final String TABLE_NAME_USER = "user";
    public static final String TABLE_NAME_PRODUCT = "product";

    // Define constants for column names in the user table
    public static final String COLUMN_NAME_USER_ID = "id";
    public static final String COLUMN_NAME_USER_NAME = "name";
    public static final String COLUMN_NAME_USER_EMAIL = "email";

    // Define constants for column names in the product table
    public static final String COLUMN_NAME_PRODUCT_ID = "id";
    public static final String COLUMN_NAME_PRODUCT_NAME = "name";
    public static final String COLUMN_NAME_PRODUCT_PRICE = "price";

    // Define SQL queries
    public static final String SQL_CREATE_USER_TABLE =
            "CREATE TABLE " + TABLE_NAME_USER + " (" +
                    COLUMN_NAME_USER_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_USER_NAME + " TEXT," +
                    COLUMN_NAME_USER_EMAIL + " TEXT)";

    public static final String SQL_CREATE_PRODUCT_TABLE =
            "CREATE TABLE " + TABLE_NAME_PRODUCT + " (" +
                    COLUMN_NAME_PRODUCT_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_PRODUCT_NAME + " TEXT," +
                    COLUMN_NAME_PRODUCT_PRICE + " REAL)";
}
