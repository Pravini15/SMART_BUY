package com.example.mad_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.AddToCartActivity;
import com.example.mad_project.R;
import com.example.mad_project.models.ImageModelClass;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.listViewHolder>{

    private static Context context;
    private ArrayList prID, prName, prPrice, prStatus;
    private ArrayList<ImageModelClass> imageModelClassList;


    public ProductAdapter(Context context, ArrayList prID, ArrayList prName, ArrayList prPrice, ArrayList prStatus, ArrayList<ImageModelClass> imageModelClassList) {
        this.context = context;
        this.prID = prID;
        this.prName = prName;
        this.prPrice = prPrice;
        this.prStatus = prStatus;
        this.imageModelClassList = imageModelClassList;
    }

    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new listViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.listViewHolder holder, int position) {
        ImageModelClass imageModelClass = imageModelClassList.get(position);
        holder.prName.setText(String.valueOf(prName.get(position)));
        holder.prPrice.setText(String.valueOf(prPrice.get(position)));
        holder.prStatus.setText(String.valueOf(prStatus.get(position)));
        holder.prImage.setImageBitmap(imageModelClass.getImage());
    }

    @Override
    public int getItemCount() {
        return prID.size();
    }

    public static class listViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView prName, prPrice, prStatus;
        ImageView prImage;

        public listViewHolder(@NonNull View itemView) {
            super(itemView);
            prName = itemView.findViewById(R.id.pr_name);
            prPrice = itemView.findViewById(R.id.pr_price);
            prStatus = itemView.findViewById(R.id.pr_status);
            prImage = itemView.findViewById(R.id.pr_image);
            itemView.setOnClickListener(this);
        }

        @Override


        public void onClick(View view) {

            int position = getAdapterPosition();

            String devname = prName.getText().toString();
            String devprice = prPrice.getText().toString();
            String devstatus = prStatus.getText().toString();


            Intent intent = new Intent(context, AddToCartActivity.class);
            intent.putExtra("keyname",devname);
            intent.putExtra("keyprice",devprice);
            intent.putExtra("keystatus" ,devstatus);
            context.startActivity(intent);

        }
    }


}