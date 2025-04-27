package com.example.trial3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {
    Button backButton;
    private Map<String, Boolean> roomAvailability;
    private Map<String, Integer> roomBookingsCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeRoomAvailability();

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button selectRoomButton1 = findViewById(R.id.selectRoomButton1);
        Button selectRoomButton2 = findViewById(R.id.selectRoomButton2);
        Button selectRoomButton3 = findViewById(R.id.selectRoomButton3);
        Button selectRoomButton4 = findViewById(R.id.selectRoomButton4);
        Button selectRoomButton5 = findViewById(R.id.selectRoomButton5);
        Button selectRoomButton6 = findViewById(R.id.selectRoomButton6);

        selectRoomButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRoomSelection("Room 101", R.drawable.one, "Room 101", 1, 6800);
            }
        });

        selectRoomButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRoomSelection("Room 102", R.drawable.one, "Room 102", 1, 5800);
            }
        });
        selectRoomButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRoomSelection("Room 103", R.drawable.one, "Room 103", 1, 6800);
            }
        });
        selectRoomButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRoomSelection("Room 104", R.drawable.one, "Room 104", 1, 5800);
            }
        });
        selectRoomButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRoomSelection("Room 105", R.drawable.one, "Room 105", 1, 5800);
            }
        });
        selectRoomButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRoomSelection("Room 106", R.drawable.one, "Room 106", 1, 5800);
            }
        });
        // Implement click listeners for other room buttons similarly

        // Implement click listeners for other room buttons similarly
    }

    private void initializeRoomAvailability() {
        roomAvailability = new HashMap<>();
        roomBookingsCount = new HashMap<>();

        // Initialize availability and booking count for each room
        roomAvailability.put("Room 101", true);
        roomAvailability.put("Room 102", true);
        roomAvailability.put("Room 103", true);
        roomAvailability.put("Room 104", true);
        roomAvailability.put("Room 105", true);
        roomAvailability.put("Room 106", true);

        roomBookingsCount.put("Room 101", 0);
        roomBookingsCount.put("Room 102", 0);
        roomBookingsCount.put("Room 103", 0);
        roomBookingsCount.put("Room 104", 0);
        roomBookingsCount.put("Room 105", 0);
        roomBookingsCount.put("Room 106", 0);
    }

    private void handleRoomSelection(String roomTitle, int roomImageResId, String roomDescription, int maxOccupancy, int price) {
        if (!roomAvailability.get(roomTitle)) {
            Toast.makeText(Register.this, "Sorry, one patient per session.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (maxOccupancy == 2 && roomBookingsCount.get(roomTitle) >= 2) {
            Toast.makeText(Register.this, "Sorry, one patient per session.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (maxOccupancy == 1 && roomBookingsCount.get(roomTitle) >= 1) {
            Toast.makeText(Register.this, "Sorry, one patient per session.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the room is one of the single occupancy rooms
        if ((roomTitle.equals("Room 101") || roomTitle.equals("Room 103")) && roomBookingsCount.get(roomTitle) >= 1) {
            Toast.makeText(Register.this, "Sorry, one patient per session.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, SessionDescription.class);
        intent.putExtra("roomTitle", roomTitle);
        intent.putExtra("roomImageResId", roomImageResId);
        intent.putExtra("roomDescription", roomDescription);
        intent.putExtra("price", price); // Pass the price to the RoomDescription activity
        startActivity(intent);

        roomAvailability.put(roomTitle, false);
        roomBookingsCount.put(roomTitle, roomBookingsCount.get(roomTitle) + 1);
    }
}