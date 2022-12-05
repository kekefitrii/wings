package com.ike.wingsshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ike.wingsshop.Database.orderContract;

public class MainActivity_Checkout extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public CartAdapter mAdapter;
    public static final int LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_checkout);

        Button bt_confirm = findViewById(R.id.bt_confirm);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int confirmOrder = getContentResolver().delete(orderContract.orderEntity.CONTENT_URI, null, null);

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
}