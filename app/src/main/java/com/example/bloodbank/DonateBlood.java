package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class   DonateBlood extends AppCompatActivity {


    EditText edfullname,edemail,edpassword,edgender,edbloodgroup,edaddress,edcity,edphoneno;
    Button register;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_blood);

        edfullname=findViewById(R.id.FullName);
        edemail=findViewById(R.id.Email);
        edpassword=findViewById(R.id.Password);
        edgender=findViewById(R.id.Gender);
        edbloodgroup=findViewById(R.id.BloodGroup);
        edaddress=findViewById(R.id.Address);
        edcity=findViewById(R.id.City);
        edphoneno=findViewById(R. id.Phoneno);
        register=findViewById(R.id.Register);

        fStore=FirebaseFirestore.getInstance();



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname=edfullname.getText().toString();
                String email=edemail.getText().toString();
                String password=edpassword.getText().toString();
                String gender=edgender.getText().toString();
                String bloodgroup=edbloodgroup.getText().toString();
                String address=edaddress.getText().toString();
                String city=edcity.getText().toString();
                String phoneno=edphoneno.getText().toString();

                Map<String,Object> donor = new HashMap<>();

                donor.put("Fullname",fullname);
                donor.put("Email",email);
                donor.put("Password",password);
                donor.put("Gender",gender);
                donor.put("BloodGroup",bloodgroup);
                donor.put("Address",address);
                donor.put("City",city);
                donor.put("PhoneNo",phoneno);

                fStore.collection("donors").add(donor).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(DonateBlood.this,"Donor Added",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        String error = e.getMessage();
                        Toast.makeText(DonateBlood.this,"Error"+ error,Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}