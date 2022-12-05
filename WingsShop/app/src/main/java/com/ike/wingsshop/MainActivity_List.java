package com.ike.wingsshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ike.wingsshop.ListOrder.ListOrderAdapter;
import com.ike.wingsshop.ListOrder.Model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_List extends AppCompatActivity {

    List<Model> modelList;
    RecyclerView recyclerView;
    ListOrderAdapter listOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);


        modelList = new ArrayList<>();
        modelList.add(new Model("So Klin Pewangi", "13cm x 10cm", 13500, 15000,R.drawable.ic_soklin_pewangi));
        modelList.add(new Model("GIV Baru", "13cm x 10cm", 11000, 0,R.drawable.ic_giv_baru));
        modelList.add(new Model("So Klin Liquid", "13cm x 10cm", 18000, 0,R.drawable.ic_soklin_liquid));
        modelList.add(new Model("GIV Kuning", "13cm x 10cm", 10000, 0,R.drawable.ic_giv_kuning));


        recyclerView = findViewById(R.id.rv_wings);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));

        listOrderAdapter = new ListOrderAdapter(this, modelList);
        recyclerView.setAdapter(listOrderAdapter);
    }
}