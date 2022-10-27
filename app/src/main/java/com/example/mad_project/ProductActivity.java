package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mad_project.adapter.ProductAdapter;
import com.example.mad_project.models.ImageModelClass;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    DBHelper dbHelper;
    ArrayList<String > prID, prName, prPrice, prStatus;
    ArrayList<ImageModelClass> prImage;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        recyclerView = findViewById(R.id.all_products);

        dbHelper = new DBHelper(ProductActivity.this);
        prID = new ArrayList<>();
        prName = new ArrayList<>();
        prPrice = new ArrayList<>();
        prStatus = new ArrayList<>();
        prImage = new ArrayList<>();

        getAllProducts();

        productAdapter = new ProductAdapter(ProductActivity.this,prID,prName,prPrice,prStatus,prImage);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductActivity.this));
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
                byte[] imageByte = cursor.getBlob(4);
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                prImage.add(new ImageModelClass(objectBitmap));
            }
        }
    }


}