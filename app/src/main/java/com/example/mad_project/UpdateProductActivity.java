package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateProductActivity extends AppCompatActivity {

    EditText upName, upPrice, upStatus;
    Button upButton;

    String id, name, price, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        upButton = findViewById(R.id.btn_updateProduct);
        upName = findViewById(R.id.et_prNameUp);
        upPrice = findViewById(R.id.et_prPriceUp);
        upStatus = findViewById(R.id.et_prStatusUp);

        getAndSetIntentData();

        upButton.setOnClickListener(v -> {

            DBHelper myDB = new DBHelper(UpdateProductActivity.this);
            name = upName.getText().toString().trim();
            price = upPrice.getText().toString().trim();
            status = upStatus.getText().toString().trim();
            myDB.updateProductData(id, name, price, status);
        });

    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("price") &&
                getIntent().hasExtra("status")){

            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            price = getIntent().getStringExtra("price");
            status = getIntent().getStringExtra("status");

            upName.setText(name);
            upPrice.setText(price);
            upStatus.setText(status);

        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}