package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    Button addButton, adminViewProducts;
    TextView prCount;
    String count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        addButton = findViewById(R.id.btn_dashAddProduct);
        adminViewProducts = findViewById(R.id.btn_adminViewProducts);
        prCount = findViewById(R.id.tv_prCount);

        DBHelper db = new DBHelper(DashboardActivity.this);

        count = String.valueOf(db.getProductsCount());

        prCount.setText(count);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, AddProduct.class));
            }
        });

        adminViewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, AdminProductViewActivity.class));
            }
        });

    }
}