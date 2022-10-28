package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mad_project.adapter.EventAndOfferAdapter;
import com.example.mad_project.models.ImageModelClass;

import java.util.ArrayList;

public class EventAndOffersActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    DBHelper dbHelper;
    ArrayList<String> eoID, eoStartDate, eoEndDate, eoDescription;
    ArrayList<ImageModelClass> eoImage;
    EventAndOfferAdapter eventAndOfferAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_and_offers);

        recyclerView = findViewById(R.id.all_event_and_offers);

        dbHelper = new DBHelper(EventAndOffersActivity.this);
        eoID = new ArrayList<>();
        eoStartDate = new ArrayList<>();
        eoEndDate = new ArrayList<>();
        eoDescription = new ArrayList<>();
        eoImage = new ArrayList<>();

        getAllEventAndOffers();

        eventAndOfferAdapter = new EventAndOfferAdapter(EventAndOffersActivity.this,eoID,eoStartDate,eoEndDate,eoDescription,eoImage);
        recyclerView.setAdapter(eventAndOfferAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(EventAndOffersActivity.this));
    }

    public void getAllEventAndOffers(){
        Cursor cursor = dbHelper.readEventAndOffer();

        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                eoID.add(cursor.getString(0));
                eoStartDate.add(cursor.getString(1));
                eoEndDate.add(cursor.getString(2));
                eoDescription.add(cursor.getString(3));
                byte[] imageByte = cursor.getBlob(4);
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                eoImage.add(new ImageModelClass(objectBitmap));
            }
        }

    }
}