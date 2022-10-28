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

    private static final String TABLE_USER = "user";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_PHONE_NUMBER = "user_phone_number";
    private static final String USER_ADDRESS = "user_address";

    private static final String TABLE_EVENT_OFFERS = "event_offer";
    private static final String EVENT_OFFER_ID = "event_offer_id";
    private static final String EVENT_OFFER_START_DATE = "event_offer_start_date";
    private static final String EVENT_OFFER_END_DATE = "event_offer_end_date";
    private static final String EVENT_OFFER_DESCRIPTION = "event_offer_description";
    private static final String EVENT_OFFER_IMAGE = "event_offer_image";


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

        String query2 = "CREATE TABLE "+ TABLE_USER + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                USER_NAME + " TEXT, " +
                USER_EMAIL + " TEXT, " +
                USER_PASSWORD + "TEXT, " +
                USER_PHONE_NUMBER + "TEXT, "+
                USER_ADDRESS + "TEXT);";
        db.execSQL(query2);

        String query3 = "CREATE TABLE "+ TABLE_EVENT_OFFERS + "(" + EVENT_OFFER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "+
                EVENT_OFFER_START_DATE + " TEXT, " +
                EVENT_OFFER_END_DATE + " TEXT, " +
                EVENT_OFFER_DESCRIPTION + " TEXT, " +
                EVENT_OFFER_IMAGE + " TEXT);";
        db.execSQL(query3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DELIVERY);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_OFFERS);
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

    public Boolean insertUserData(String user_name, String user_email, String user_password, String user_phone_number, String user_address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_NAME, user_name);
        cv.put(USER_EMAIL,user_email);
        cv.put(USER_PASSWORD,user_password);
        cv.put(USER_PHONE_NUMBER,user_phone_number);
        cv.put(USER_ADDRESS,user_address);


        long result = db.insert(TABLE_USER, null,cv);
        if (result == -1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from TABLE_USER ",null);
       return cursor;
    }

    public void addEventAndOffers(String start_date, String end_date, String description, ImageModelClass imageModelClass){

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

        cv.put(EVENT_OFFER_START_DATE, start_date);
        cv.put(EVENT_OFFER_END_DATE, end_date);
        cv.put(EVENT_OFFER_DESCRIPTION, description);
        cv.put(EVENT_OFFER_IMAGE, imageToByte);

        long result = db.insert(TABLE_EVENT_OFFERS, null,cv);
        if (result == -1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readEventAndOffer(){
        String query3 = "SELECT * FROM " + TABLE_EVENT_OFFERS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query3,null);
        }
        return cursor;
    }


}
