package com.example.mad_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mad_project.models.ImageModelClass;

import java.io.ByteArrayOutputStream;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "SMARTBUY.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "productName";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_IMAGE = "image";
    
    private static final String TABLE_DELIVERY = "delivery_details";
    private static final String DELIVERY_ID = "billing_id";
    private static final String DELIVERY_NAME = "billing_name";
    private static final String CONTACT_NO = "contact_no";
    private static final String PROVINCE = "province";
    private static final String DISTRICT = "district";
    private static final String CITY = "city";
    private static final String ADDRESS = "address";

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageToByte;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_STATUS + " TEXT," +
                COLUMN_IMAGE + " BLOB);";
        db.execSQL(query);
        
        String query1 = "CREATE TABLE "+ TABLE_DELIVERY + " (" + DELIVERY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DELIVERY_NAME + " TEXT, " +
                CONTACT_NO + " TEXT, " +
                PROVINCE + " TEXT, " +
                DISTRICT + " TEXT, " +
                CITY + " TEXT, " +
                ADDRESS + " TEXT);" ;
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DELIVERY);
        onCreate(db);
    }

    public void addProduct(String name, String price, String status, ImageModelClass imageModelClass){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        try {

            Bitmap imageToStoreBitmap = imageModelClass.getImage();

            byteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

            imageToByte = byteArrayOutputStream.toByteArray();

        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_STATUS, status);
        cv.put(COLUMN_IMAGE, imageToByte);

        long result = db.insert(TABLE_NAME, null,cv);
        if (result == -1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readProducts(){
         String query = "SELECT * FROM " + TABLE_NAME;
         SQLiteDatabase db = this.getReadableDatabase();

         Cursor cursor = null;
         if (db != null){
             cursor = db.rawQuery(query,null);
         }
         return cursor;
    }
    
    public void addBillingDetails(String billing_name, String contact_no, String province, String district, String city, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DELIVERY_NAME, billing_name);
        cv.put(CONTACT_NO, contact_no);
        cv.put(PROVINCE, province);
        cv.put(DISTRICT, district);
        cv.put(CITY, city);
        cv.put(ADDRESS, address);


        long result = db.insert(TABLE_DELIVERY, null,cv);
        if (result == -1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    void updateProductData(String row_id, String name, String price, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_STATUS, status);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});

        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }
}
