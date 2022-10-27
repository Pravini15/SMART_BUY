package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    TextView registerpage;
    RelativeLayout btn_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerpage = findViewById(R.id.register);


        String textregister = "<font color = #0096FF ><b>SMART</b></font><font color= ##D7FCEE><b>BUY</b></font>";
        registerpage.setText(Html.fromHtml(textregister));

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();

            }
        });
    }

    private void RegisterUser(){
        Intent intent = new Intent(RegisterActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}