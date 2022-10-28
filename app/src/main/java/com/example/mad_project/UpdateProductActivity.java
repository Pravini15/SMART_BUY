package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mad_project.models.ImageModelClass;

import java.io.IOException;

public class UpdateProductActivity extends AppCompatActivity {

    EditText upName, upPrice, upStatus;
    Button upButton, deleteButton, getImage;

    ImageView imageView;
    Bitmap image;

    String id, name, price, status;

    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        upButton = findViewById(R.id.btn_updateProduct);
        upName = findViewById(R.id.et_prNameUp);
        upPrice = findViewById(R.id.et_prPriceUp);
        upStatus = findViewById(R.id.et_prStatusUp);
        deleteButton = findViewById(R.id.btn_delete_product);
        getImage = findViewById(R.id.btn_get_imageUp);
        imageView = findViewById(R.id.img_pr_update);

        getAndSetIntentData();

        upButton.setOnClickListener(v -> {

            DBHelper myDB = new DBHelper(UpdateProductActivity.this);
            name = upName.getText().toString().trim();
            price = upPrice.getText().toString().trim();
            status = upStatus.getText().toString().trim();
            myDB.updateProductData(id, name, price, status, new ImageModelClass(image));
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete();
            }
        });

        getImage.setOnClickListener(v -> {
            imageChooser();
        });

    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("price") &&
                getIntent().hasExtra("status")){

            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            price = getIntent().getStringExtra("price");
            status = getIntent().getStringExtra("status");

            upName.setText(name);
            upPrice.setText(price);
            upStatus.setText(status);

        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper myDB = new DBHelper(UpdateProductActivity.this);
                myDB.deleteProduct(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImageUri);

                }catch (IOException e){
                    e.printStackTrace();
                }
                imageView.setImageBitmap(image);
            }
        }
    }
}