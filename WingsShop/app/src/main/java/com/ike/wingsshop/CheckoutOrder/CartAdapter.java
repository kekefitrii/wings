package com.ike.wingsshop.CheckoutOrder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.ike.wingsshop.Database.orderContract;
import com.ike.wingsshop.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends CursorAdapter {

    ArrayList<Checkout> items2 = new ArrayList<>();
    int total = 0;

    public CartAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.checkout_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nameofcart, priceofcart, quantityofcart;
        ImageView imagesofcart;

        nameofcart = view.findViewById(R.id.item_name);
        priceofcart = view.findViewById(R.id.item_price);
        quantityofcart = view.findViewById(R.id.item_pcs);
        imagesofcart = view.findViewById(R.id.iv_list);

        int id = cursor.getColumnIndex(orderContract.orderEntity._ID);
        int name = cursor.getColumnIndex(orderContract.orderEntity.COLUMN_NAME);
        int price = cursor.getColumnIndex(orderContract.orderEntity.COLUMN_PRICE);
        int quantity = cursor.getColumnIndex(orderContract.orderEntity.COLUMN_QUANTITY);
        int images = cursor.getColumnIndex(orderContract.orderEntity.COLUMN_IMAGE);

        String nameOrder = cursor.getString(name);
        String priceOrder = cursor.getString(price);
        String quantityOrder = cursor.getString(quantity);
        String imagesOrder = cursor.getString(images);

        int price1 = cursor.getInt(price);

        int resID = context.getResources().getIdentifier(imagesOrder , "drawable", context.getPackageName());
        nameofcart.setText(nameOrder);
        priceofcart.setText("Rp " + NumberFormat.getInstance().format(Integer.parseInt(priceOrder)) + ",-");
        quantityofcart.setText(quantityOrder);
        imagesofcart.setImageResource(resID);

        total = total + Integer.parseInt(priceOrder);
        Intent i = new Intent("MyTotal");
        i.putExtra("total1", total);

        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
    }
}
