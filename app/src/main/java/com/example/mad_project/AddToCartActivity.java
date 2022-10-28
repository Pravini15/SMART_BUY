package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddToCartActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ImageView imageView, plusquantity, minusquantity, addtocart;
    TextView quantitynumber, itemname, itemstatus, itemprice;
    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        imageView = findViewById(R.id.imageView9);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity = findViewById(R.id.minusimage);
        quantitynumber = findViewById(R.id.textView9);
        itemname = findViewById(R.id.devicename);
        itemprice = findViewById(R.id.textView6);
        itemstatus = findViewById(R.id.status);
        addtocart = findViewById(R.id.imageView10);

        String device = getIntent().getStringExtra("keyname");
        String devprice = getIntent().getStringExtra("keyprice");
        String devstatus = getIntent().getStringExtra("keystatus");

        itemname.setText(device);
        itemprice.setText(devprice);
        itemstatus.setText(devstatus);

        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int basePrice = 450000;
                quantity++;
                displayQuantity();
                int phoneprice = basePrice * quantity;
                String setnewPrice = String.valueOf(phoneprice);
                itemprice.setText(setnewPrice);

            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int basePrice = 450000;
                if (quantity == 0) {
                    Toast.makeText(AddToCartActivity.this, "Cant decrease the quantity < 0", Toast.LENGTH_SHORT).show();
                }else {
                    quantity--;
                    displayQuantity();
                    int phoneprice = basePrice * quantity;
                    String setnewPrice = String.valueOf(phoneprice);
                    itemprice.setText(setnewPrice);
                }
            }
        });

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(AddToCartActivity.this);
                dbHelper.add_to_cart(itemname.getText().toString().trim(),
                        itemprice.getText().toString().trim(),
                        itemstatus.getText().toString().trim(),
                        quantitynumber.getText().toString().trim());

            }
        });
    }

    private void displayQuantity(){
        quantitynumber.setText(String.valueOf(quantity));
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COL_NAME,
                OrderContract.OrderEntry.COL_PRICE,
                OrderContract.OrderEntry.COL_STATUS,
                OrderContract.OrderEntry.COL_QUANTITY};
        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() <1){
            return;
        }
        if (cursor.moveToFirst())  {
            int name = cursor.getColumnIndex(OrderContract.OrderEntry.COL_NAME);
            int price = cursor.getColumnIndex(OrderContract.OrderEntry.COL_PRICE);
            int status = cursor.getColumnIndex(OrderContract.OrderEntry.COL_STATUS);
            int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.COL_QUANTITY);

            String devicename = cursor.getString(name);
            String devprice = cursor.getString(price);
            String devstatus = cursor.getString(status);
            String devquantity = cursor.getString(quantity);

            itemname.setText(devicename);
            itemprice.setText(devprice);
            itemstatus.setText(devstatus);
            quantitynumber.setText(devquantity);

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        itemname.setText("");
        itemprice.setText("");
        itemstatus.setText("");
        quantitynumber.setText("");

    }
}