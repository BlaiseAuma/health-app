package com.example.trial3;
import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FormActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_CODE = 100;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Check and request permissions if not granted
        if (!checkPermission()) {
            requestPermission();
        }

        // Retrieve user details and room details from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        double amountPaid = intent.getDoubleExtra("amountPaid", 0.0);
        String roomSelected = intent.getStringExtra("roomSelected");

        // Display user and room details in TextViews
        TextView nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText("Name: " + name);
        TextView emailTextView = findViewById(R.id.emailTextView);
        emailTextView.setText("Email: " + email);
        TextView phoneTextView = findViewById(R.id.phoneTextView);
        phoneTextView.setText("Phone: " + phone);
        TextView amountPaidTextView = findViewById(R.id.amountPaidTextView);
        amountPaidTextView.setText("Amount Paid: Kes" + amountPaid);
        TextView roomSelectedTextView = findViewById(R.id.roomSelectedTextView);
        roomSelectedTextView.setText("Room Selected: " + roomSelected);

        // Set click listener for download button
        Button downloadButton = findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate receipt text
                String receiptContent = "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone +
                        "\nAmount Paid: Kes" + amountPaid + "\nRoom Selected: " + roomSelected;

                // Save receipt to a file
                saveReceiptToFile(receiptContent);
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveReceiptToFile(String receiptContent) {
        // Create a directory to store receipts
        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Receipts");

        // Check if external storage is available for read and write
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "External storage not available", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                Toast.makeText(this, "Failed to create directory", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Create a file to store the receipt
        File receiptFile = new File(directory, "receipt.txt");
        try {
            // Write receipt content to the file
            FileWriter writer = new FileWriter(receiptFile);
            writer.append(receiptContent);
            writer.flush();
            writer.close();

            // Display success message
            Toast.makeText(this, "Receipt downloaded successfully", Toast.LENGTH_SHORT).show();

            // Initiate download using DownloadManager
            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.fromFile(receiptFile);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Receipts/receipt.txt");
            downloadManager.enqueue(request);
        } catch (IOException e) {
            e.printStackTrace();
            // Display error message
            Toast.makeText(this, "Failed to download receipt", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

