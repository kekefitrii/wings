package com.ike.wingsshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ike.wingsshop.ListOrder.ListOrderAdapter;
import com.ike.wingsshop.ListOrder.Model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_List extends AppCompatActivity implements View.OnClickListener {

    List<Model> modelList;
    RecyclerView recyclerView;
    ListOrderAdapter listOrderAdapter;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        modelList = new ArrayList<>();
        modelList.add(new Model("So Klin Pewangi", "13cm x 10cm", 13500, 15000, R.drawable.ic_soklin_pewangi));
        modelList.add(new Model("GIV Baru", "13cm x 10cm", 11000, 0, R.drawable.ic_giv_baru));
        modelList.add(new Model("GIV Pink", "13cm x 10cm", 10000, 0, R.drawable.ic_giv_pink));
        modelList.add(new Model("GIV Biru", "13cm x 10cm", 10500, 0, R.drawable.ic_giv_biru));
        modelList.add(new Model("GIV Putih", "13cm x 10cm", 11000, 0, R.drawable.ic_giv_putih));
        modelList.add(new Model("So Klin Liquid", "13cm x 10cm", 18000, 0, R.drawable.ic_soklin_liquid));
        modelList.add(new Model("So Klin Liquid Biru", "13cm x 10cm", 15000, 0, R.drawable.ic_soklin_liquid_biru));
        modelList.add(new Model("So Klin Liquid Ungu", "13cm x 10cm", 16500, 0, R.drawable.ic_soklin_liquid_ungu));
        modelList.add(new Model("GIV Lemon", "13cm x 10cm", 10000, 0, R.drawable.ic_giv_kuning));
        modelList.add(new Model("GIV Mulberry", "13cm x 10cm", 11000, 0, R.drawable.ic_giv_mulberry));
        modelList.add(new Model("GIV Sakura", "13cm x 10cm", 12000, 0, R.drawable.ic_giv_sakura));
        modelList.add(new Model("GIV Bengkoang", "13cm x 10cm", 11500, 0, R.drawable.ic_giv_bengkoang));

        recyclerView = findViewById(R.id.rv_wings);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));

        listOrderAdapter = new ListOrderAdapter(this, modelList);
        recyclerView.setAdapter(listOrderAdapter);

        checkout = findViewById(R.id.bt_checkout);
        checkout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_checkout: {
                Intent i = new Intent(this, MainActivity_Checkout.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
            break;
        }
    }
}