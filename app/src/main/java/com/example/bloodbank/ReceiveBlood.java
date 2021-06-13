package com.example.bloodbank;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

public class ReceiveBlood extends Fragment  {

    private View view;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CollectionReference cref;
    FirebaseUser fUser;



    Spinner bloodgroup;
    Button btnsearch;
    List<DonorData> donorItem;
    private RecyclerView recyclerView;
    private ReceiveBloodAdapter radapter;

    public ReceiveBlood(){

    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

     view = inflater.inflate(R.layout.activity_receive_blood, container,false);

     fAuth=FirebaseAuth.getInstance();
     fStore=FirebaseFirestore.getInstance();
     fUser=fAuth.getCurrentUser();

     cref=fStore.collection("donors");


     bloodgroup= view.findViewById(R.id.spinner);
     btnsearch= view.findViewById(R.id.Search);



     btnsearch.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             donorItem = new ArrayList<>();
             donorItem.clear();
             radapter= new ReceiveBloodAdapter(donorItem);

             recyclerView = (RecyclerView) view.findViewById(R.id.donorid);
             recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
             RecyclerView.LayoutManager searchdonor = new LinearLayoutManager(getContext());

             recyclerView.setLayoutManager(searchdonor);
             recyclerView.setItemAnimator(new DefaultItemAnimator());
             recyclerView.addItemDecoration(new DividerItemDecoration( getActivity(), LinearLayoutManager.VERTICAL));

             recyclerView.setAdapter(radapter);
             Task<QuerySnapshot> qpath= cref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                 @Override
                 public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                     if (!queryDocumentSnapshots.isEmpty()) {

                         for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                             DonorData donorData = (DonorData) documentSnapshot.toObject(DonorData.class);
                             donorItem.add(donorData);
                             radapter.notifyDataSetChanged();

                         }
                     } else {
                         Toast.makeText(getActivity(), "Database is empty now!",
                                 Toast.LENGTH_LONG).show();
                     }
                 }


             });














         }
     });


      return view;
    }



}