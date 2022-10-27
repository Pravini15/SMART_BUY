package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mad_project.adapter.UserAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


    }
}