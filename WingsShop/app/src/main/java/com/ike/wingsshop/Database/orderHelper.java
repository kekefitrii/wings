package com.ike.wingsshop.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class orderHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "orders.db";

    public orderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_TABLE = "CREATE TABLE " + orderContract.orderEntity.TABLE_NAME + " ("
                + orderContract.orderEntity._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + orderContract.orderEntity.COLUMN_NAME + " TEXT NOT NULL, "
                + orderContract.orderEntity.COLUMN_QUANTITY + " TEXT NOT NULL, "
                + orderContract.orderEntity.COLUMN_IMAGE + " TEXT NOT NULL, "
                + orderContract.orderEntity.COLUMN_PRICE + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
