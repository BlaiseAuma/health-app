package com.example.trial3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnrollmentActivity extends AppCompatActivity {

    // Declare the fields for patient data
    EditText patientNameEditText;
    EditText patientAgeEditText;
    Button enrollButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);

        // Initialize views
        patientNameEditText = findViewById(R.id.patientName);
        patientAgeEditText = findViewById(R.id.patientAge);
        enrollButton = findViewById(R.id.enrollConfirmButton);

        // Set the Enroll button click listener
        enrollButton.setOnClickListener(v -> enrollPatient());
    }

    // Function to enroll the patient
    private void enrollPatient() {
        String patientName = patientNameEditText.getText().toString();
        String patientAge = patientAgeEditText.getText().toString();

        if (patientName.isEmpty() || patientAge.isEmpty()) {
            showToast("Please fill in all fields.");
        } else {
            // Logic to enroll the patient can go here
            showToast("Patient enrolled successfully");
        }
    }

    // Function to display Toast messages
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

