package com.example.trial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {


    private EditText editTextUsername, editTextPassword,editTextemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        editTextemail = findViewById(R.id.editTextemail);
        TextView textViewAlreadyHaveAccount = findViewById(R.id.textViewAlreadyHaveAccount);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String email = editTextemail.getText().toString().trim(); // Add this line to get email

                // Perform registration
                boolean registrationSuccess = registerUser(Registration.this, username, password, email);

                if (registrationSuccess) {
                    Toast.makeText(Registration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registration.this, LogIn.class));
                    finish(); // Close the registration activity
                } else {
                    Toast.makeText(Registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up clickable text for "Already have an account? Login"
        textViewAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, LogIn.class));
                finish(); // Close the registration activity
            }
        });
    }

    private boolean registerUser(Context context, String username, String password, String email) {
        // Implement registration logic (insert data into SQLite database)
        // Return true if registration is successful, false otherwise

        SQLiteDatabase db = null;
        try {
            MyDbHelper dbHelper = new MyDbHelper(context); // Pass the context to MyDbHelper constructor
            db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DatabaseContract.COLUMN_NAME_USER_NAME, username);
            values.put(DatabaseContract.COLUMN_NAME_USER_EMAIL, password);

            long newRowId = db.insert(DatabaseContract.TABLE_NAME_USER, null, values);

            // Check if the insertion was successful
            return newRowId != -1;
        } catch (SQLiteException e) {
            // Handle database exception
            e.printStackTrace();
            return false; // Registration failed due to database error
        } finally {
            if (db != null && db.isOpen()) {
                db.close(); // Close the database connection
            }
        }
    }
}