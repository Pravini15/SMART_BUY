package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView loginpage;
    TextView app_signIn;
    EditText app_username, app_password;
    RelativeLayout btn_login;
    private String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String mobilePattern = "^\\+[0-9]{10,13}$";
    private String passwordPattern = "[a-zA-Z0-9\\\\!\\\\@\\\\#\\\\$]{8,24}";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginpage = findViewById(R.id.textView);
        app_signIn = findViewById(R.id.app_sign);
        app_username = findViewById(R.id.editText);
        app_password = findViewById(R.id.editText2);
        btn_login = findViewById(R.id.btn_login);

        String textlogin = "<font color = #0096FF ><b>SMART</b></font><font color= ##D7FCEE><b>BUY</b></font>";
        loginpage.setText(Html.fromHtml(textlogin));

        String text = "<font color = #FF000000> <b>Don't have an account?</b></font> <font color = #187bcd> <b>SingUp</b></font>";
        app_signIn.setText(Html.fromHtml(text));

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
                LogUser();

            }
        });
    }

    private void LogUser() {
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);

    }

    private void validateFields() {
        String username,password;
        username = app_username.getText().toString();
        password = app_password.getText().toString();

        if (!(username.isEmpty())){
            if (!(password.isEmpty())){
                if (username.matches(EmailPattern)){
                    if (password.matches(passwordPattern)){
                        Toast.makeText(LoginActivity.this,"Hello user "+ username,Toast.LENGTH_SHORT).show();
                    }else {
                        app_password.setError("Password length should 8-24");
                    }
                }else {
                    app_username.setError("Invalid password");
                }
            }else {
                app_password.setError("Fill this ");
            }
        }else {
            app_username.setError("Fill this");
        }



    }
}