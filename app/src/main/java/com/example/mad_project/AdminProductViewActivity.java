package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mad_project.adapter.AdminProductsAdapter;
import com.example.mad_project.models.ImageModelClass;

import java.util.ArrayList;

public class AdminProductViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    DBHelper dbHelper;
    ArrayList<String > prID, prName, prPrice, prStatus;
    ArrayList<ImageModelClass> prImage;
    AdminProductsAdapter adminProductsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_view);

        recyclerView = findViewById(R.id.admin_pr_table);

        dbHelper = new DBHelper(AdminProductViewActivity.this);
        prID = new ArrayList<>();
        prName = new ArrayList<>();
        prPrice = new ArrayList<>();
        prStatus = new ArrayList<>();
        prImage = new ArrayList<>();

        getAllProducts();

        adminProductsAdapter = new AdminProductsAdapter(AdminProductViewActivity.this,prID,prName,prPrice,prStatus);
        recyclerView.setAdapter(adminProductsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminProductViewActivity.this));
    }

    public void getAllProducts(){
        Cursor cursor = dbHelper.readProducts();

        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                prID.add(cursor.getString(0));
                prName.add(cursor.getString(1));
                prPrice.add(cursor.getString(2));
                prStatus.add(cursor.getString(3));
            }
        }
    }

}