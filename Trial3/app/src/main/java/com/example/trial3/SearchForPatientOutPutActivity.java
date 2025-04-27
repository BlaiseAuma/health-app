package com.example.trial3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchForPatientOutPutActivity extends AppCompatActivity {
    TextView nameTextView,phoneNumberTextView,complainTextView;
    Button btnBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_patient_out_put);

        nameTextView = findViewById(R.id.nameTextView);
        phoneNumberTextView = findViewById(R.id.phoneNumberTextView);
        complainTextView = findViewById(R.id.complainTextView);
        btnBack = findViewById(R.id.btnBack);

        String name = getIntent().getStringExtra("name");
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        String expertise = getIntent().getStringExtra("expertise");


        // Display the retrieved data in TextViews

        nameTextView.setText("Name: " + name);
        phoneNumberTextView.setText("Phone Number: " + phoneNumber);
        complainTextView.setText("Expertise: " + expertise);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchForPatientOutPutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}