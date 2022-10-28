package com.example.mad_project.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.R;
import com.example.mad_project.UpdateProductActivity;

import java.util.ArrayList;

public class AdminProductsAdapter extends RecyclerView.Adapter<AdminProductsAdapter.ProductTableViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList prID, prName, prPrice, prStatus;

    public AdminProductsAdapter(Activity activity, Context context, ArrayList prID, ArrayList prName, ArrayList prPrice, ArrayList prStatus) {
        this.activity = activity;
        this.context = context;
        this.prID = prID;
        this.prName = prName;
        this.prPrice = prPrice;
        this.prStatus = prStatus;
    }

    @NonNull
    @Override
    public AdminProductsAdapter.ProductTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_item_admin, parent, false);
        return new ProductTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminProductsAdapter.ProductTableViewHolder holder, int position) {

        holder.prID.setText(String.valueOf(prID.get(position)));
        holder.prName.setText(String.valueOf(prName.get(position)));
        holder.prPrice.setText(String.valueOf(prPrice.get(position)));
        holder.prStatus.setText(String.valueOf(prStatus.get(position)));
        holder.upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateProductActivity.class);
                intent.putExtra("id", String.valueOf(prID.get(position)));
                intent.putExtra("name", String.valueOf(prName.get(position)));
                intent.putExtra("price", String.valueOf(prPrice.get(position)));
                intent.putExtra("status", String.valueOf(prStatus.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prID.size();
    }

    public static class ProductTableViewHolder extends RecyclerView.ViewHolder{

        TextView prID, prName, prPrice, prStatus;
        Button upButton, deleteButton;
        LinearLayout linearLayout;

        public ProductTableViewHolder(@NonNull View itemView) {
            super(itemView);

            prID = itemView.findViewById(R.id.admin_pr_id);
            prName = itemView.findViewById(R.id.admin_pr_name);
            prPrice = itemView.findViewById(R.id.admin_pr_price);
            prStatus = itemView.findViewById(R.id.admin_pr_status);
            upButton = itemView.findViewById(R.id.btn_update_product);
            deleteButton = itemView.findViewById(R.id.btn_delete_product);
            linearLayout = itemView.findViewById(R.id.layout_pr_item);
        }
    }

}
