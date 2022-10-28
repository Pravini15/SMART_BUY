package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.ims.ImsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mad_project.models.ImageModelClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddEventAndOffersActivity extends AppCompatActivity {

    EditText eoStartDate, eoEndDate, eoDescription;
    Button addBtn, insertImg, viewImg;
    ImageView imageView;
    Bitmap image;
    byte[] byteImage;

    int SELECT_PICTURE = 200;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_and_offers);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        eoStartDate = findViewById(R.id.eo_startdate);
        eoEndDate = findViewById(R.id.eo_enddate);
        eoDescription = findViewById(R.id.eo_description);
        addBtn = findViewById(R.id.btn_addEventOffer);
        insertImg = findViewById(R.id.btn_get_image);
        imageView = findViewById(R.id.img_eo_insert);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(AddEventAndOffersActivity.this);

                dbHelper.addEventAndOffers(eoStartDate.getText().toString().trim(),
                        eoEndDate.getText().toString().trim(),
                        eoDescription.getText().toString().trim(),
                        new ImageModelClass(image));

            }
        });

        insertImg.setOnClickListener(v -> {
            imageChooser();
        });
    }

    void imageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == SELECT_PICTURE){
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