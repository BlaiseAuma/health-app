package com.example.trial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private TextView textViewSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.textViewSignUp);

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, Registration.class));
                finish(); // Close the registration activity
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                // Check username and password against the database
                if (isValidCredentials(username, password)) {
                    // Successful login
                    startActivity(new Intent(LogIn.this, MainActivity.class));
                    finish(); // Close the login activity
                } else {
                    // Failed login
                    Toast.makeText(LogIn.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            MyDbHelper dbHelper = new MyDbHelper(this);
            db = dbHelper.getReadableDatabase();

            // Query the database for the user with the given username and password
            String[] projection = {
                    DatabaseContract.COLUMN_NAME_USER_ID
            };
            String selection = DatabaseContract.COLUMN_NAME_USER_NAME + " = ? AND " +
                    DatabaseContract.COLUMN_NAME_USER_EMAIL + " = ?";
            String[] selectionArgs = {username, password};

            cursor = db.query(
                    DatabaseContract.TABLE_NAME_USER,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            // If the cursor has rows, it means the user exists with the given username and password
            return cursor.getCount() > 0;
        } catch (SQLiteException e) {
            // Handle database exception
            e.printStackTrace();
            return false;
        } finally {
            // Close the cursor and database to release resources
            if (cursor != null) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }
}