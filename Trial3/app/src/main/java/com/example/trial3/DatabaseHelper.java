package com.example.trial3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "patients.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_PATIENTS = "patients";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_ADDRESS = "address";

    // SQL statement for creating the table
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_PATIENTS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_AGE + " TEXT, " +
            COLUMN_PHONE + " TEXT, " +
            COLUMN_ADDRESS + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
        onCreate(db);
    }

    // Method to insert patient data
    public void registerPatient(String name, String age, String phone, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_ADDRESS, address);
        db.insert(TABLE_PATIENTS, null, values);
        db.close();
    }

    // Method to search for a patient by name
    public String searchPatient(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PATIENTS + " WHERE " + COLUMN_NAME + "=?", new String[]{name});

        if (cursor.moveToFirst()) {
            String age = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AGE));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS));
            cursor.close();
            db.close();
            return "Patient Found:\nName: " + name + "\nAge: " + age + "\nPhone: " + phone + "\nAddress: " + address;
        } else {
            cursor.close();
            db.close();
            return "Patient not found.";
        }
    }
}
