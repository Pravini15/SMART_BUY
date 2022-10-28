package com.example.mad_project;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderProvider extends ContentProvider {

    public DBHelper mHelper;
    public static final int ORDER = 100;
    public static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(OrderContract.CONTENT_AUTHORITY, OrderContract.PATH, ORDER);
    }

    @Override
    public boolean onCreate() {
        mHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case ORDER:
                cursor = database.query(OrderContract.OrderEntry.TABLE_NAME, strings, s, strings1,null, null, s1);
                break;

            default:
                throw new IllegalArgumentException("CAN'T QUERY");
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;

    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case ORDER:
                return insertCart(uri, values);

            default:
                throw new IllegalArgumentException("Cant insert data");
        }
    }

    private Uri insertCart(Uri uri, ContentValues values) {

        String name = values.getAsString(OrderContract.OrderEntry.COL_NAME);
        if(name==null){
            throw new IllegalArgumentException("Name is Required");

        }
        String price = values.getAsString(OrderContract.OrderEntry.COL_PRICE);
        if(price==null){
            throw new IllegalArgumentException("Price is Required");

        }
        String status = values.getAsString(OrderContract.OrderEntry.COL_STATUS);
        if(status==null){
            throw new IllegalArgumentException("Status is Required");
        }
        String quantity = values.getAsString(OrderContract.OrderEntry.COL_QUANTITY);
        if(quantity==null){
            throw new IllegalArgumentException("Quantity is Required");

        }

        SQLiteDatabase database = mHelper.getWritableDatabase();
        long id = database.insert(OrderContract.OrderEntry.TABLE_NAME, null, values);

        if (id == -1) {
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {

        int rowDeleted;
        SQLiteDatabase database = mHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match)
        {
            case ORDER:
                rowDeleted = database.delete(OrderContract.OrderEntry.TABLE_NAME, s, strings);
                break;

            default:
                throw new IllegalArgumentException("Can't delete");

        }

        if (rowDeleted!=0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
