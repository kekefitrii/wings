package com.ike.wingsshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ike.wingsshop.Database.orderContract;

import java.text.NumberFormat;

public class MainActivity_Detail extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    ImageView iv_detail;
    ImageButton plus_quantity, minus_quantity;
    TextView name_detail, tv_price, tv_dimention, quantity_number, total_price_detail;
    int quantity;
    Button bt_buy;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;
    public static final String KEY_PRICE = "price";
    public static final String KEY_PRICE2 = "price2";
    public static final String KEY_IMAGE = "images";
    public static final int PRICE = 0;
    public static final int PRICE2 = 0;
    public static final int IMAGES = 0;
    private String name_, dimention_;
    private int price_, price2_, images_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);


        Intent in = getIntent();
        name_ = in.getStringExtra("name");
        dimention_ = in.getStringExtra("dimention");
        price_ = in.getIntExtra(KEY_PRICE, PRICE);
        price2_ = in.getIntExtra(KEY_PRICE2, PRICE2);
        images_ = in.getIntExtra(KEY_IMAGE, IMAGES);

        iv_detail = findViewById(R.id.iv_detail);
        name_detail = findViewById(R.id.name_detail);
        tv_price = findViewById(R.id.tv_price);
        total_price_detail = findViewById(R.id.total_price_detail);
        tv_dimention = findViewById(R.id.tv_dimention);
        plus_quantity = findViewById(R.id.addquantity);
        minus_quantity = findViewById(R.id.subquantity);
        quantity_number = findViewById(R.id.quantity);
        bt_buy = findViewById(R.id.button_buy);

        name_detail.setText(name_);
        tv_dimention.setText(dimention_);
        tv_price.setText("Rp " + NumberFormat.getInstance().format(price_) + ",-");
        total_price_detail.setText("Rp 0,-");
        iv_detail.setImageResource(images_);
        bt_buy.setOnClickListener(this);

        plus_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                displayQuantity();
                int orderPrice = price_ * quantity;
                String setTotal = NumberFormat.getInstance().format(orderPrice);
                total_price_detail.setText("Rp " + setTotal + ",-");
            }
        });

        minus_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantity == 0) {
                    Toast.makeText(MainActivity_Detail.this, "Quantity tidak boleh < 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    int orderPrice = price_ * quantity;
                    String setTotal = NumberFormat.getInstance().format(orderPrice);
                    total_price_detail.setText("Rp " + setTotal + ",-");
                }
            }
        });
    }

    private void displayQuantity() {
        quantity_number.setText(String.valueOf(quantity));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_buy: {
                if (quantity == 0) {
                    Toast.makeText(MainActivity_Detail.this, "Quantity tidak boleh 0", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity_Detail.this, MainActivity_Checkout.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                    Savecart();
                }
            }
            break;
        }
    }

    private boolean Savecart() {

        String name = name_detail.getText().toString();
        String price = total_price_detail.getText().toString();
        String quantity = quantity_number.getText().toString();

        ContentValues values = new ContentValues();
        values.put(orderContract.orderEntity.COLUMN_NAME, name);
        values.put(orderContract.orderEntity.COLUMN_PRICE, price);
        values.put(orderContract.orderEntity.COLUMN_QUANTITY, quantity);
        values.put(orderContract.orderEntity.COLUMN_IMAGE, images_);

        if (mCurrentCartUri == null) {
            Uri newUri = getContentResolver().insert(orderContract.orderEntity.CONTENT_URI, values);

            if (newUri == null) {
                Toast.makeText(this, "Failed to add to cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Success to add to cart", Toast.LENGTH_SHORT).show();
            }
        }
        hasAllRequiredValues = true;
        return hasAllRequiredValues;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {orderContract.orderEntity._ID,
                orderContract.orderEntity.COLUMN_NAME,
                orderContract.orderEntity.COLUMN_PRICE,
                orderContract.orderEntity.COLUMN_QUANTITY,
                orderContract.orderEntity.COLUMN_IMAGE
        };

        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if (cursor.moveToFirst()) {
            int name = cursor.getColumnIndex(orderContract.orderEntity.COLUMN_NAME);
            int price = cursor.getColumnIndex(orderContract.orderEntity.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(orderContract.orderEntity.COLUMN_QUANTITY);
            int images = cursor.getColumnIndex(orderContract.orderEntity.COLUMN_IMAGE);

            String nameOrder = cursor.getString(name);
            String priceOrder = cursor.getString(price);
            String quantityOrder = cursor.getString(quantity);

            name_detail.setText(nameOrder);
            total_price_detail.setText(priceOrder);
            quantity_number.setText(quantityOrder);
            iv_detail.setImageResource(images);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        int imageResource = getResources().getIdentifier("@drawable/ic_shop", null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        name_detail.setText("");
        total_price_detail.setText("");
        quantity_number.setText("");
        iv_detail.setImageDrawable(res);
    }
}