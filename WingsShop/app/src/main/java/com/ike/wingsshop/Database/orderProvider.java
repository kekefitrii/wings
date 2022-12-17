package com.ike.wingsshop.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.ike.wingsshop.MainActivity_Checkout;


public class orderProvider extends ContentProvider {

    public static final int ORDER = 100;

    public static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(orderContract.CONTENT_AUTHORITY, orderContract.PATH, ORDER);
    }

    public orderHelper mHelper;

    @Override
    public boolean onCreate() {
        mHelper = new orderHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {

        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case ORDER:
                cursor = database.query(orderContract.orderEntity.TABLE_NAME, strings, s, strings1, null, null, s1);
                break;

            default:
                throw new IllegalArgumentException("Cant Query");
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        int match = sUriMatcher.match(uri);
        switch (match) {
            case ORDER:
                return insertCart(uri, contentValues);

            default:
                throw new IllegalArgumentException("Cant Insert Data");
        }

    }

    private Uri insertCart(Uri uri, ContentValues contentValues) {
        String name = contentValues.getAsString(orderContract.orderEntity.COLUMN_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Name is Required");
        }
        String price = contentValues.getAsString(orderContract.orderEntity.COLUMN_PRICE);
        if (price == null) {
            throw new IllegalArgumentException("price is Required");
        }
        String quantity = contentValues.getAsString(orderContract.orderEntity.COLUMN_QUANTITY);
        if (quantity == null) {
            throw new IllegalArgumentException("Name is Required");
        }

        String images = contentValues.getAsString(orderContract.orderEntity.COLUMN_IMAGE);
        if (images == null) {
            throw new IllegalArgumentException("Images is Required");
        }

        SQLiteDatabase database = mHelper.getWritableDatabase();
        long id = database.insert(orderContract.orderEntity.TABLE_NAME, null, contentValues);

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
        switch (match) {
            case ORDER:
                rowDeleted = database.delete(orderContract.orderEntity.TABLE_NAME, s, strings);
                break;

            default:
                throw new IllegalArgumentException("Cannot Delete");
        }

        if (rowDeleted!=0) {
            getContext().getContentResolver().notifyChange(uri, null);

        }
        return rowDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
    public String getSum(){

        SQLiteDatabase database = mHelper.getWritableDatabase();
        String amount;
        String quary = "SELECT SUM(" + orderContract.orderEntity.COLUMN_PRICE + ") FROM " + orderContract.orderEntity.TABLE_NAME;
        Cursor cursor = database.rawQuery(quary, null);
        if(cursor.moveToFirst()){
            amount = String.valueOf(cursor.getInt(0));
        } else {
            amount = "0";
        }
        cursor.close();
        database.close();
        return amount;


    }
}
