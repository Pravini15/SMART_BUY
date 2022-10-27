package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeliveryActivity extends AppCompatActivity {

    EditText editfullname, editTextPhoneNo, editTextBillingAddress, editTextTextDistrict, editTextTextCity, editTextTextdeliveryadd;
    Button billingbtn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        editfullname = findViewById(R.id.editTextfullname);
        editTextPhoneNo = findViewById(R.id.editTextPhoneNo);
        editTextBillingAddress = findViewById(R.id.editTextBillingAddress);
        editTextTextDistrict = findViewById(R.id.editTextTextDistrict);
        editTextTextCity = findViewById(R.id.editTextTextCity);
        editTextTextdeliveryadd = findViewById(R.id.editTextTextdeliveryadd);
        billingbtn = findViewById(R.id.billingbtn);

        billingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(DeliveryActivity.this);
                dbHelper.addBillingDetails(editfullname.getText().toString().trim(),
                        editTextPhoneNo.getText().toString().trim(),
                        editTextBillingAddress.getText().toString().trim(),
                        editTextTextDistrict.getText().toString().trim(),
                        editTextTextCity.getText().toString().trim(),
                        editTextTextdeliveryadd.getText().toString().trim());
            }
        });
    }
}