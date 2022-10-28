package com.example.mad_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{

    Context context;
    ArrayList dev_id, dev_name, dev_price, dev_status, dev_quantity;

    CartAdapter(Context context, ArrayList dev_id, ArrayList dev_name,
                ArrayList dev_price, ArrayList dev_status,
                ArrayList dev_quantity){
        this.context = context;
        this.dev_id = dev_id;
        this.dev_name = dev_name;
        this.dev_price = dev_price;
        this.dev_status = dev_status;
        this.dev_quantity = dev_quantity;
    }
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView item_name, item_price, item_status, item_quantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.pr_name);
            item_price = itemView.findViewById(R.id.pr_price);
            item_status = itemView.findViewById(R.id.pr_status);
            item_quantity = itemView.findViewById(R.id.pr_quantity);
        }
    }
}

