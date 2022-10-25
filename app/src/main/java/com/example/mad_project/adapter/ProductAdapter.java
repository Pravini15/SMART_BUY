package com.example.mad_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.R;
import com.example.mad_project.models.Products;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.listViewHolder>{

    Context context;
    List<Products> mData;
    private listViewHolder.RecyclerViewClickListener clickListener;

    public ProductAdapter(Context context, List<Products> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new listViewHolder(layout,context,mData,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, int position) {
        String name = mData.get(position).getName().toString();
        String price = mData.get(position).getPrize().toString();
        String status = mData.get(position).getStatus().toString();

        holder.name.setText(name);
        holder.price.setText(price);
        holder.status.setText(status);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class listViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name, price, status;
        ImageView pr;

        public listViewHolder(@NonNull View itemView, Context context, List<Products> mData, RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.pr_name);
            price = itemView.findViewById(R.id.pr_price);
            status = itemView.findViewById(R.id.pr_status);
        }

        @Override
        public void onClick(View v) {

        }

        public interface RecyclerViewClickListener{

        }
    }
}