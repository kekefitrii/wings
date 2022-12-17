package com.ike.wingsshop.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class orderHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Penjualan.db";
    public static orderHelper mHelper;
    public orderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static orderHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new orderHelper(context);
        }
        return mHelper;
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

//    public int sumPriceCartItems() {
//        int result = 0;
//        SQLiteDatabase database = mHelper.getWritableDatabase();
//        Cursor cursor = database.rawQuery("SELECT SUM("+ orderContract.orderEntity.COLUMN_PRICE + ") FROM " + orderContract.orderEntity.TABLE_NAME, null);
//        if (cursor.moveToFirst()) result = cursor.getInt(0);
//        cursor.close();
//        database.close();
//        return result;
//    }

    public String getSum(SQLiteDatabase database){

        String amount;
        String quary = "SELECT SUM(" + orderContract.orderEntity.COLUMN_PRICE + ") FROM " + orderContract.orderEntity.TABLE_NAME;
        Cursor cursor = database.rawQuery(quary, null);
        if(cursor.moveToFirst()){
            amount = String.valueOf(cursor.getInt(0));
        } else {
            amount = "10";
        }
        cursor.close();
        database.close();
        return amount;


    }
}

