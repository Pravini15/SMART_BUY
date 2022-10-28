package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.window.SplashScreen;

import com.example.mad_project.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    TextView registerpage;
    EditText name,email,password,phonenumber,address;
    RelativeLayout btn_signUp;
    DBHelper dbHelper;
    RelativeLayout signUpAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.user_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phonenumber = findViewById(R.id.phone_number);
        address = findViewById(R.id.address);
        signUpAcc = findViewById(R.id.btn_signUp);
        dbHelper = new DBHelper(this);

        String textregister = "<font color = #0096FF ><b>SMART</b></font><font color= ##D7FCEE><b>BUY</b></font>";
        registerpage.setText(Html.fromHtml(textregister));

        signUpAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // RegisterUser();
                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String phonenumber1 = phonenumber.getText().toString();
                String address1 = address.getText().toString();
                boolean b = dbHelper.insertUserData(name1,email1,password1,phonenumber1,address1);
                if (b){
                    Toast.makeText(RegisterActivity.this,"Data insert",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(RegisterActivity.this,"Failed to insert Data",Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerpage = findViewById(R.id.loginAcc);
        registerpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}