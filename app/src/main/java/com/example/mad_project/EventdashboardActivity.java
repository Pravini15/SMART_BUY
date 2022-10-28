package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EventdashboardActivity extends AppCompatActivity {

    Button viewButton, addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdashboard);

        viewButton = findViewById(R.id.btn_dashView);
        addButton = findViewById(R.id.btn_dashAdd);

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventdashboardActivity.this, EventAndOffersActivity.class));
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventdashboardActivity.this, AddEventAndOffersActivity.class));
            }
        });
    }
}