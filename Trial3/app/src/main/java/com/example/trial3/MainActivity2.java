package com.example.trial3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    // Declare the necessary buttons
    Button hivAidsButton;
    Button enrollButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize buttons
        hivAidsButton = findViewById(R.id.hivAidsButton);  // The "HIV/AIDS Program" button
        enrollButton = findViewById(R.id.enrollButton);    // The "Enroll" button

        // Set the "HIV/AIDS Program" button functionality (You can add any logic you want here)
        hivAidsButton.setOnClickListener(v -> {
            // If you want to show a message when the HIV/AIDS button is clicked
            showToast("HIV/AIDS Program selected");
        });

        // Set the "Enroll" button functionality
        enrollButton.setOnClickListener(v -> {
            // Navigate to the Enrollment screen (where users can enroll patients)
            openEnrollmentPage();
        });
    }

    // Function to navigate to Enrollment Activity
    private void openEnrollmentPage() {
        Intent intent = new Intent(MainActivity2.this, EnrollmentActivity.class);
        startActivity(intent);  // Start the Enrollment Activity
    }

    // Function to display Toast messages
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
