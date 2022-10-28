package com.example.mad_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.R;
import com.example.mad_project.models.ImageModelClass;

import java.util.ArrayList;

public class EventAndOfferAdapter extends RecyclerView.Adapter<EventAndOfferAdapter.eoListViewHolder>{

    private Context context;
    private ArrayList eoID, eoStartDate, eoEndDate, eoDescription;
    private ArrayList<ImageModelClass> imageModelClassList;

    public EventAndOfferAdapter(Context context, ArrayList eoID, ArrayList eoStartDate, ArrayList eoEndDate, ArrayList eoDescription, ArrayList<ImageModelClass> imageModelClassList){
        this.context = context;
        this.eoID = eoID;
        this.eoStartDate = eoStartDate;
        this.eoEndDate = eoEndDate;
        this.eoDescription = eoDescription;
        this.imageModelClassList = imageModelClassList;
    }
    @NonNull
    @Override
    public eoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.event_and_offer_item, parent, false);
        return new eoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAndOfferAdapter.eoListViewHolder holder, int position) {
        ImageModelClass imageModelClass = imageModelClassList.get(position);
        holder.eoId.setText(String.valueOf(eoID.get(position)));
        holder.eoStartDate.setText(String.valueOf(eoStartDate.get(position)));
        holder.eoEndDate.setText(String.valueOf(eoEndDate.get(position)));
        holder.eoDescription.setText(String.valueOf(eoDescription.get(position)));
        holder.eoImage.setImageBitmap(imageModelClass.getImage());
    }

    public int getItemCount(){
        return eoID.size();
    }

    public static class eoListViewHolder extends RecyclerView.ViewHolder{

        TextView eoId, eoStartDate, eoEndDate, eoDescription;
        ImageView eoImage;

        public eoListViewHolder(@NonNull View evetOfferview){
            super(evetOfferview);
            eoStartDate = evetOfferview.findViewById(R.id.eo_start_date);
            eoEndDate = evetOfferview.findViewById(R.id.eo_end_date);
            eoDescription = evetOfferview.findViewById(R.id.eo_description);
            eoImage = evetOfferview.findViewById(R.id.eo_image);

        }
    }


}
