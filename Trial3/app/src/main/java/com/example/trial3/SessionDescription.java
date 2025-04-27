package com.example.trial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SessionDescription extends AppCompatActivity {
    TextView roomTitleTextView, roomDescriptionTextView, roomPriceTextView;
    ImageView roomImageView;
    Button selectRoomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_description);

        // Get room details from Intent extras
        String roomTitle = getIntent().getStringExtra("roomTitle");
        int roomImageResId = getIntent().getIntExtra("roomImageResId", 0);
        String roomDescription = getIntent().getStringExtra("roomDescription");
        int price = getIntent().getIntExtra("price", 0);

        // Initialize views
        roomTitleTextView = findViewById(R.id.roomTitleTextView);
        roomImageView = findViewById(R.id.roomImageView);
        roomDescriptionTextView = findViewById(R.id.roomDescriptionTextView);
        selectRoomButton = findViewById(R.id.selectRoomButton);
        roomPriceTextView = findViewById(R.id.roomPriceTextView);

        // Set room details into the views
        if (roomImageResId != 0) {
            roomImageView.setImageResource(roomImageResId); // Set the image if it's valid
        }
        roomTitleTextView.setText(roomTitle);
        roomDescriptionTextView.setText(roomDescription);
        roomPriceTextView.setText("Kes " + price); // Add "Kes" before the price

        // Set the click listener for the select room button
        selectRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to pass data to the next activity
                Intent secureRoomIntent = new Intent(SessionDescription.this, RegisterPatient.class);
                secureRoomIntent.putExtra("price", price); // Pass the price (no need to cast to double if not required)

                // Start RegisterPatient activity
                startActivity(secureRoomIntent);

                // Finish current activity (optional, if you want to close this one)
                finish();
            }
        });
    }
}
