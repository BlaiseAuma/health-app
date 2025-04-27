package com.example.trial3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    TextView textView1,textView2,textView3,textView4;
    Button backButton;
    ScrollView scrollView;
    ImageView imageView,imageViewRules;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Initialize views
        scrollView = findViewById(R.id.scrollView);
        textView1 = findViewById(R.id.article);
        textView2 = findViewById(R.id.subabout);
        textView3 = findViewById(R.id.subarticle);
        backButton = findViewById(R.id.backButton);

        // Set click listener for back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}