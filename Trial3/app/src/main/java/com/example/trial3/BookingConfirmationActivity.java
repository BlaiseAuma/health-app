package com.example.trial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookingConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);



        // Retrieve room details from the intent
        String roomDetails = getRoomDetails();

        // Set room details text in TextView
        TextView roomDetailsTextView = findViewById(R.id.roomDetailsContentTextView);
        roomDetailsTextView.setText(roomDetails);

        // Retrieve additional data from the intent
        Intent intent = getIntent();
        String course = intent.getStringExtra("course");
        String year = intent.getStringExtra("year");
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        String roomName = intent.getStringExtra("roomName");
        double price = getIntent().getDoubleExtra("price", 0.0);
        String roomDescription = intent.getStringExtra("roomDescription");

        // Concatenate all details into a single string
        String userDetails = "Course: " + course + "\n"
                + "Year: " + year + "\n"
                + "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Phone: " + phone + "\n"
                + "Room Name: " + roomName + "\n"
                + "price: Kes" + price + "\n"
                + "Room Description: " + roomDescription;

        // Display the concatenated details in a single TextView
        TextView userDetailsTextView = findViewById(R.id.roomDetailsContentTextView);
        userDetailsTextView.setText(userDetails);

        // Set click listener for confirm button
        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent receiptIntent = new Intent(BookingConfirmationActivity.this, FormActivity.class);
                receiptIntent.putExtra("name", name);
                receiptIntent.putExtra("email", email);
                receiptIntent.putExtra("phone", phone);
                receiptIntent.putExtra("amountPaid", price); // Assuming the amount paid is the room price
                receiptIntent.putExtra("roomSelected", roomName);
                startActivity(receiptIntent);
            }
        });
    }

private String getRoomDetails() {
        // Retrieve room details from intent extras
        String roomTitle = getIntent().getStringExtra("roomTitle");
        String roomDescription = getIntent().getStringExtra("roomDescription");
        double price = getIntent().getDoubleExtra("price", 0.0);

        // Construct room details string
        return "Title: " + roomTitle + "\nDescription: " + roomDescription + "\nPrice: Kes" + price;
        }
        }
