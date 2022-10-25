package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProduct extends AppCompatActivity {

    EditText prName, prPrice, prStatus;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        prName = findViewById(R.id.et_prName);
        prPrice = findViewById(R.id.et_prPrice);
        prStatus = findViewById(R.id.et_prStatus);
        addBtn = findViewById(R.id.btn_addProduct);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(AddProduct.this);
                dbHelper.addProduct(prName.getText().toString().trim(),
                        prPrice.getText().toString().trim(),
                        prStatus.getText().toString().trim());
            }
        });
    }
}