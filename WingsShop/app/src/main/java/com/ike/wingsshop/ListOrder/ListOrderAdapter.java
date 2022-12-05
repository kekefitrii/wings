package com.ike.wingsshop.ListOrder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ike.wingsshop.MainActivity_Detail;
import com.ike.wingsshop.R;

import java.text.NumberFormat;
import java.util.List;

public class ListOrderAdapter extends RecyclerView.Adapter<ListOrderAdapter.ViewHolder> {

    List<Model> modelList;
    Context context;
    private String name, dimention;
    private int price, price2, images;

    public ListOrderAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listorder_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        name = modelList.get(position).getName();
        price = modelList.get(position).getPrice();
        price2 = modelList.get(position).getPrice2();
        images = modelList.get(position).getImage();
        dimention = modelList.get(position).getDimention();

        holder.item_name.setText(name);
        holder.item_price.setText("Rp " + NumberFormat.getInstance().format(price) + ",-");

        if (price2 == 0) {
            holder.item_price2.setVisibility(View.GONE);
        } else {
            holder.item_price2.setText("Rp " + NumberFormat.getInstance().format(price2) + ",-");
        }
        holder.iv_list.setImageResource(images);

        holder.bt_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent i = new Intent(context, MainActivity_Detail.class);
                i.putExtra("name", modelList.get(position).getName());
                i.putExtra("dimention", modelList.get(position).getDimention());
                i.putExtra("price", modelList.get(position).getPrice());
                i.putExtra("price2", modelList.get(position).getPrice2());
                i.putExtra("images", modelList.get(position).getImage());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button bt_buy;
        TextView item_name, item_price, item_price2;
        ImageView iv_list;
        public ViewHolder(View itemView) {
            super(itemView);

            item_name = itemView.findViewById(R.id.item_name);
            item_price = itemView.findViewById(R.id.item_price);
            item_price2 = itemView.findViewById(R.id.item_price2);
            iv_list = itemView.findViewById(R.id.iv_list);
            bt_buy = itemView.findViewById(R.id.bt_buy);

        }
    }
}
