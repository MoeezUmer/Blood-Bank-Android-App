package com.example.bloodbank;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ReceiveBloodAdapter extends RecyclerView.Adapter<ReceiveBloodAdapter.PostHolder> {

    FirebaseFirestore fStore;

    public List<DonorData> postLists;


    public class PostHolder extends RecyclerView.ViewHolder{

        TextView Name, Address,City,PhoneNo;


        public PostHolder(@NonNull View itemView) {
            super(itemView);
            Name= itemView.findViewById(R.id.FullName);
            Address=itemView.findViewById(R.id.Address);
            City=itemView.findViewById(R.id.City);
            PhoneNo=itemView.findViewById(R.id.Phoneno);

        }
    }

    public ReceiveBloodAdapter(List<DonorData> postLists){
        this.postLists=postLists;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View listitem = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_donate_blood,viewGroup, false);

        return new PostHolder(listitem);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder postHolder, int i) {

        if (i % 2 == 0) {
            postHolder.itemView.setBackgroundColor(Color.parseColor("#C13F31"));

        } else {
            postHolder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }

        DonorData donorData= postLists.get(i);

        postHolder.Name.setText("Name :"+donorData.getName());
        postHolder.Address.setText("Address :"+donorData.getAddress());
        postHolder.City.setText("Address :"+donorData.getCity());
        postHolder.PhoneNo.setText("Address :"+donorData.getPhoneNo());




    }
    @Override
    public int getItemCount() {
        return postLists.size();
    }






}
