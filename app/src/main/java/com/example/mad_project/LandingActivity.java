package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class LandingActivity extends AppCompatActivity {

    TextView landinglogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        landinglogo = findViewById(R.id.logo);

        String textlogo = "<font color = #0096FF ><b>SMART</b></font><font color= ##D7FCEE><b>BUY</b></font>";
        landinglogo.setText(Html.fromHtml(textlogo));
    }
}