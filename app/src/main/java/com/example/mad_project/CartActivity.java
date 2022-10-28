package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mad_project.adapter.CartAdapter;
import com.example.mad_project.models.ImageModelClass;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    CartAdapter mAdapter;
    DBHelper dbHelper;
    ArrayList<String> devID, devName, devPrice, devStatus, devQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cartlist);

        dbHelper = new DBHelper(CartActivity.this);
        devID = new ArrayList<>();
        devName = new ArrayList<>();
        devPrice = new ArrayList<>();
        devStatus = new ArrayList<>();
        devQuantity = new ArrayList<>();

        displayData();



    }

    void displayData(){
        Cursor cursor = dbHelper.readCartItems();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data.", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                devID.add(cursor.getString(0));
                devName.add(cursor.getString(1));
                devPrice.add(cursor.getString(2));
                devStatus.add(cursor.getString(3));

            }
        }
    }


}