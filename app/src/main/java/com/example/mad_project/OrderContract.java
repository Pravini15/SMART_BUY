package com.example.mad_project;

import android.net.Uri;
import android.provider.BaseColumns;

public class OrderContract {

    public OrderContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.example.mad_project";
    public static final Uri BASE_URI = Uri.parse(("content://" + CONTENT_AUTHORITY));
    public static final String PATH  = "cart";

    public static abstract class OrderEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, PATH);

        public static final String TABLE_NAME = "cart";
        public static final String _ID = BaseColumns._ID;
        public static final String COL_NAME = "dev_name";
        public static final String COL_PRICE = "dev_price";
        public static final String COL_STATUS = "dev_status";
        public static final String COL_QUANTITY = "quantity";
    }


}
