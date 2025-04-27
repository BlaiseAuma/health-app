package com.example.trial3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Searchforpatient extends AppCompatActivity {

    private EditText nameEditText;
    private TextView resultTextView;
    private Button searchButton, backButton;
    private DatabaseHelper databaseHelper; // <--- ADD THIS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchforpatient);

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText);
        resultTextView = findViewById(R.id.resultTextView);
        searchButton = findViewById(R.id.searchButton);
        backButton = findViewById(R.id.backButton);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this); // <--- Initialize it

        // Set search button click listener
        searchButton.setOnClickListener(v -> performSearch());

        // Set back button click listener
        backButton.setOnClickListener(v -> onBackPressed());
    }

    private void performSearch() {
        String name = nameEditText.getText().toString().trim();

        if (!name.isEmpty()) {
            // Search from database
            String result = databaseHelper.searchPatient(name);

            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setText(result); // Show the result found from database
        } else {
            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setText("Please enter a patient's name to search.");
        }
    }
}
