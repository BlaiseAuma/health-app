package com.example.trial3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class secondActivity extends AppCompatActivity {
    private LinearLayout cartLayout;
    private ArrayList<String> itemNames = new ArrayList<>();
    private ArrayList<Integer> itemTotalPrices = new ArrayList<>();
    private TextView totalPriceTextView; // TextView for displaying the total price

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        cartLayout = findViewById(R.id.cartLayout);
        totalPriceTextView = findViewById(R.id.totalPriceTextView); // Initialize totalPriceTextView

        ArrayList<String> selectedItems = getIntent().getStringArrayListExtra("itemsData");
        if (selectedItems != null) {
            for (String itemData : selectedItems) {
                String[] parts = itemData.split(" - ");
                if (parts.length == 2) {
                    String itemName = parts[0].trim();
                    int itemPrice = Integer.parseInt(parts[1].substring(1));
                    if (!itemName.isEmpty() && itemPrice > 0) {
                        addItemToCart(itemName, itemPrice);
                    }
                }
            }
        }

        Button checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(v -> {
            if (!itemNames.isEmpty() && !itemTotalPrices.isEmpty()) {
                // Now you have the item names and total prices, you can pass them to the CheckoutActivity
                Intent intent = new Intent(secondActivity.this, checkOutActivity.class);
                intent.putStringArrayListExtra("itemNames", itemNames);
                intent.putIntegerArrayListExtra("itemTotalPrices", itemTotalPrices);
                startActivity(intent);
            } else {
                // Handle case where no items are in the cart or item information is not valid
                Toast.makeText(secondActivity.this, "Invalid item information or empty cart", Toast.LENGTH_SHORT).show();
            }
        });

        // Update the total price initially
        updateTotalPrice();
    }

    private void addItemToCart(String itemName, int itemPrice) {
        itemNames.add(itemName);  // Add item name to the list
        itemTotalPrices.add(itemPrice);  // Add item price to the list

        // Create a new item view and add it to the cartLayout
        View cartItemView = LayoutInflater.from(this).inflate(R.layout.cart_item_layout, cartLayout, false);
        TextView itemNameTextView = cartItemView.findViewById(R.id.itemNameTextView);
        TextView itemPriceTextView = cartItemView.findViewById(R.id.itemPriceTextView);
        TextView quantityTextView = cartItemView.findViewById(R.id.quantityTextView);
        TextView totalPriceTextView = cartItemView.findViewById(R.id.totalPriceTextView);
        Button increaseButton = cartItemView.findViewById(R.id.increaseButton);
        Button decreaseButton = cartItemView.findViewById(R.id.decreaseButton);
        Button removeButton = cartItemView.findViewById(R.id.removeButton);

        // Set item name and price in the cart item view
        itemNameTextView.setText(itemName);
        itemPriceTextView.setText("kes " + itemPrice);

        increaseButton.setOnClickListener(v -> {
            // Increment quantity
            int quantity = Integer.parseInt(quantityTextView.getText().toString());
            quantity++;
            quantityTextView.setText(String.valueOf(quantity));

            // Calculate and update total price
            int newTotalPrice = quantity * itemPrice;
            totalPriceTextView.setText("Total: kes " + newTotalPrice);

            // Update total price in the itemTotalPrices list
            int index = itemNames.indexOf(itemName);
            if (index != -1) {
                itemTotalPrices.set(index, newTotalPrice);
            }

            // Update the overall total price
            updateTotalPrice();
        });

        decreaseButton.setOnClickListener(v -> {
            // Decrease quantity if greater than 1
            int quantity = Integer.parseInt(quantityTextView.getText().toString());
            if (quantity > 1) {
                quantity--;
                quantityTextView.setText(String.valueOf(quantity));

                // Calculate and update total price
                int newTotalPrice = quantity * itemPrice;
                totalPriceTextView.setText("Total: kes " + newTotalPrice);

                // Update total price in the itemTotalPrices list
                int index = itemNames.indexOf(itemName);
                if (index != -1) {
                    itemTotalPrices.set(index, newTotalPrice);
                }

                // Update the overall total price
                updateTotalPrice();
            }
        });

        removeButton.setOnClickListener(v -> {
            // Remove the item view from the cartLayout
            cartLayout.removeView(cartItemView);

            // Remove the item name and total price from their respective lists
            int index = itemNames.indexOf(itemName);
            if (index != -1) {
                itemNames.remove(index);
                itemTotalPrices.remove(index);
            }

            // Update the overall total price
            updateTotalPrice();
        });

        // Add the item view to the cartLayout
        cartLayout.addView(cartItemView);

        // Update the overall total price
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        int totalPrice = 0;
        for (int price : itemTotalPrices) {
            totalPrice += price;
        }
        totalPriceTextView.setText("Total Price: kes " + totalPrice);
    }

    // Method to handle back button click
    public void goBackToMain(View view) {
        // Navigate back to MainActivity
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}

