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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private Context context;
    private ArrayList usID, usName, usEmail, usPassword, usPhonenumber, usAddress;

    public UserAdapter(Context context, ArrayList usID, ArrayList usName, ArrayList usEmail, ArrayList usPassword, ArrayList usPhonenumber, ArrayList usAddress){
        this.context = context;
        this.usID = usID;
        this.usName = usName;
        this.usEmail = usEmail;
        this.usPassword = usPassword;
        this.usPhonenumber = usPhonenumber;
        this.usAddress = usAddress;
    }

    public UserViewHolder onCreateViewHolder( ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_register, parent, false);
        return new UserViewHolder(view);
    }

    public void onBindViewHolder( UserAdapter.UserViewHolder holder, int position){
        holder.usName.setText(String.valueOf(usName.get(position)));
        holder.usEmail.setText(String.valueOf(usEmail.get(position)));
        holder.usPassword.setText(String.valueOf(usPassword.get(position)));
        holder.usPhonenumber.setText(String.valueOf(usPhonenumber.get(position)));
        holder.usAddress.setText(String.valueOf(usAddress.get(position)));
    }

    public int getItemCount(){
        return usID.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        TextView usName, usEmail, usPassword, usPhonenumber, usAddress;

        public UserViewHolder(View view){
            super(view);
            usName = view.findViewById(R.id.user_name);
            usEmail = view.findViewById(R.id.email);
            usPassword = view.findViewById(R.id.password);
            usPhonenumber = view.findViewById(R.id.phone_number);
            usAddress = view.findViewById(R.id.editText);
        }
    }
}
