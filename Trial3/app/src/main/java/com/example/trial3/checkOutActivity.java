package com.example.trial3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class checkOutActivity extends AppCompatActivity {
    private TextView totalPriceTextView;
    private TextView dateTextView;
    private TextView universityTextView;
    private LinearLayout itemListLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_check_out);

        // Initialize views
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        dateTextView = findViewById(R.id.dateTextView);
        universityTextView = findViewById(R.id.universityTextView);
        itemListLayout = findViewById(R.id.itemListLayout);

        // Set date and university name
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date todayDate = new Date();
        dateTextView.setText("Date: " + dateFormat.format(todayDate));
        universityTextView.setText("KWUST UNIVERSITY");

        // Get data from intent
        ArrayList<String> itemNames = getIntent().getStringArrayListExtra("itemNames");
        ArrayList<Integer> itemTotalPrices = getIntent().getIntegerArrayListExtra("itemTotalPrices");

        if (itemNames != null && itemTotalPrices != null && itemNames.size() == itemTotalPrices.size()) {
            // Display items in the itemListLayout
            for (int i = 0; i < itemNames.size(); i++) {
                String itemName = itemNames.get(i);
                int itemPrice = itemTotalPrices.get(i);

                // Create a TextView for each item and add it to the itemListLayout
                TextView itemTextView = new TextView(this);
                itemTextView.setText(String.format("%-20s kes %-10d", itemName, itemPrice));
                itemListLayout.addView(itemTextView);

                // Add spacing between items
                TextView spacingTextView = new TextView(this);
                spacingTextView.setText("\n");
                itemListLayout.addView(spacingTextView);
            }

            // Calculate and set total price text
            int totalPrice = calculateTotalPrice(itemTotalPrices);
            totalPriceTextView.setText("Total Price: kes " + totalPrice);
        } else {
            totalPriceTextView.setText("Error: Invalid data received");
        }
    }

    // Helper method to calculate the total price
    private int calculateTotalPrice(ArrayList<Integer> itemTotalPrices) {
        int totalPrice = 0;
        for (int price : itemTotalPrices) {
            totalPrice += price;
        }
        return totalPrice;
    }
}



