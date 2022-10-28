package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
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

import com.example.mad_project.models.ImageModelClass;

import java.io.IOException;

public class AddProduct extends AppCompatActivity {

    EditText prName, prPrice, prStatus;
    Button addBtn, insertImg, viewImg;
    ImageView imageView;
    Bitmap image;
    byte[] byteImage;

    int SELECT_PICTURE = 200;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        prName = findViewById(R.id.et_prNameUp);
        prPrice = findViewById(R.id.et_prPriceUp);
        prStatus = findViewById(R.id.et_prStatusUp);
        addBtn = findViewById(R.id.btn_updateProduct);
        insertImg = findViewById(R.id.btn_get_imageUp);
        imageView = findViewById(R.id.img_pr_update);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(AddProduct.this);

                dbHelper.addProduct(prName.getText().toString().trim(),
                        prPrice.getText().toString().trim(),
                        prStatus.getText().toString().trim(),
                        new ImageModelClass(image));
            }
        });

        insertImg.setOnClickListener(v -> {
            imageChooser();
        });



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