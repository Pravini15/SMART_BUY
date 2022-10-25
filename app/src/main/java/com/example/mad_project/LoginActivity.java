package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView loginpage;
    TextView app_signUp;
    EditText app_username, app_password;
    RelativeLayout btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginpage = findViewById(R.id.textView);
        app_signUp = findViewById(R.id.app_sign);
        app_username = findViewById(R.id.editText);
        app_password = findViewById(R.id.editText2);
        btn_login = findViewById(R.id.btn_login);

        String textlogin = "<font color = #0096FF ><b>SMART</b></font><font color= ##D7FCEE><b>BUY</b></font>";
        loginpage.setText(Html.fromHtml(textlogin));

        String text = "<font color = #FF000000> <b>Don't have an account?</b></font> <font color = #187bcd> <b>SingUp</b></font>";
        app_signUp.setText(Html.fromHtml(text));

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"Logged in successfull",Toast.LENGTH_SHORT).show();
                validateFields();
         //       LogUser();

            }
        });
    }

    private void LogUser() {

    }

    private void validateFields() {
        String username,password;
        username = app_username.getText().toString();
        password = app_password.getText().toString();



    }
}