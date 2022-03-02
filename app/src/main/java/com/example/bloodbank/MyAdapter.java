package com.example.bloodbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public Context context;
    public  ArrayList<Data> userArrayList;

    public MyAdapter(Context context, ArrayList<Data> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.listitemsingle,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        Data data= userArrayList.get(position);
        holder.Fullname.setText(data.Fullname);
        holder.Address.setText(data.Address);
        holder.City.setText(data.City);
        holder.PhoneNo.setText(data.PhoneNo);

    }

    @Override
    public int getItemCount() {

        return userArrayList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

      public  TextView Fullname, Address, City, PhoneNo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Fullname=itemView.findViewById(R.id.fullname1);
            Address=itemView.findViewById(R.id.address1);
            City=itemView.findViewById(R.id.city1);
            PhoneNo=itemView.findViewById(R.id.phoneno1);

        }
    }
}
