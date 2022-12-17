package com.ike.wingsshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ike.wingsshop.CheckoutOrder.CartAdapter;
import com.ike.wingsshop.CheckoutOrder.Checkout;
import com.ike.wingsshop.Database.orderContract;
import com.ike.wingsshop.Database.orderHelper;
import com.ike.wingsshop.Database.orderProvider;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity_Checkout extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ArrayList<Checkout> items2;
    Checkout checkout;
    public CartAdapter mAdapter;
    public static final int LOADER = 0;
    TextView tv_total;
    orderProvider mProvider;
    orderHelper mHelper;
    int totalAllAmount = 0;
    int totalBill = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_checkout);

        tv_total = findViewById(R.id.tv_totalgrand);
        tv_total.setText("Rp 0,-");

        mHelper = new orderHelper(this);
        SQLiteDatabase database = mHelper.getWritableDatabase();

//        tv_total.setText(String.format("Rp %s", mHelper.getSum(database)));

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mBroadcast, new IntentFilter("MyTotal"));

        Button bt_confirm = findViewById(R.id.bt_confirm);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        getSupportLoaderManager().initLoader(LOADER, null, this);
        ListView listView = findViewById(R.id.lv_checkout);
        mAdapter = new CartAdapter(this, null);
        listView.setAdapter(mAdapter);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {orderContract.orderEntity._ID,
                orderContract.orderEntity.COLUMN_NAME,
                orderContract.orderEntity.COLUMN_PRICE,
                orderContract.orderEntity.COLUMN_QUANTITY,
                orderContract.orderEntity.COLUMN_IMAGE
        };

        return new CursorLoader(this, orderContract.orderEntity.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    public BroadcastReceiver mBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            totalBill = intent.getIntExtra("total1", 0);

            if (totalBill == 0) {
                tv_total.setText("Rp 0,-");
            } else {
                tv_total.setText("Rp " + NumberFormat.getInstance().format(totalBill) + ",-");
            }
        }
    };

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("Checkout");
        alertDialogBuilder
                .setMessage("Apakah anda ingin melakukan checkout? \nTotal yang harus dibayar sebesar " + tv_total.getText().toString())
                .setIcon(R.drawable.ic_cart)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showNotif();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void showNotif() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Success");
        alertDialogBuilder
                .setMessage("Terima kasih telah berbelanja")
                .setIcon(R.drawable.ic_cart)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        int confirmOrder = getContentResolver().delete(orderContract.orderEntity.CONTENT_URI, null, null);
//                        SQLiteDatabase database = mHelper.getWritableDatabase();

                        tv_total.setText("Rp 0,-");
                        finish();
                        Intent i = new Intent(MainActivity_Checkout.this, MainActivity_List.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}