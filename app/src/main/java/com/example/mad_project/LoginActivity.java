package com.example.mad_project;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
    EditText app_email, app_password;
    RelativeLayout btn_login;
    DBHelper dbHelper;

    private String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String mobilePattern = "^\\+[0-9]{10,13}$";
    private String passwordPattern = "[a-zA-Z0-9\\\\!\\\\@\\\\#\\\\$]{8,24}";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Boolean e=false,p=false;
        setContentView(R.layout.activity_login);

        loginpage = findViewById(R.id.textView);
        app_email = findViewById(R.id.editText);
        app_password = findViewById(R.id.editText2);
        btn_login = findViewById(R.id.btn_login);
        dbHelper = new DBHelper(this);

        String textlogin = "<font color = #0096FF ><b>SMART</b></font><font color= ##D7FCEE><b>BUY</b></font>";
        loginpage.setText(Html.fromHtml(textlogin));

        String text = "<font color = #FF000000> <b>Don't have an account?</b></font> <font color = #187bcd> <b>SingUp</b></font>";
        app_signIn.setText(Html.fromHtml(text));

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validateFields();
                String email,password;
                email = app_email.getText().toString();
                password = app_password.getText().toString();
                Cursor cursor = dbHelper.getData();
                if (cursor.getCount() == 0){
                    Toast.makeText(LoginActivity.this,"No entries Exists",Toast.LENGTH_LONG).show();
                }
                if (loginCheck(cursor,email,password)){
                    Intent intent = new Intent(LoginActivity.this, FinalActivity.class);
                    intent.putExtra("email",email);
                    app_email.setText("");
                    app_password.setText("");
                    startActivity(intent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Wrong Credential");
                    builder.setMessage("Wrong Credential");
                    builder.show();
                }
                dbHelper.close();
            }
        });
        //logUser

        app_signIn = findViewById(R.id.app_sign);
        app_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }

        });
    }

    public static boolean loginCheck(@NonNull Cursor cursor, String email, String password){
        while (cursor.moveToNext()){
            if (cursor.getString(0).equals(email)){
                if (cursor.getString(2).equals(password)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}