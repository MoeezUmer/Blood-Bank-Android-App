package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class viewall extends AppCompatActivity {


    FirebaseFirestore fstore;
    private TextView o1, o2, a1,a2,b1,b2,ab1,ab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);
        fstore= FirebaseFirestore.getInstance();

        o1 = (TextView) findViewById(R.id.op);
        o2 = (TextView) findViewById(R.id.on);
        a1 = (TextView) findViewById(R.id.ap);
        a2 = (TextView) findViewById(R.id.an);
        b1 = (TextView) findViewById(R.id.bp);
        b2 = (TextView) findViewById(R.id.bn);
        ab1 = (TextView) findViewById(R.id.abp);
        ab2 = (TextView) findViewById(R.id.abn);
        searchFunction();
    }

    public void  searchFunction(){
        Toast.makeText(getApplicationContext(), "Loading...." , Toast.LENGTH_SHORT).show();
        fstore.collection("donors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int total, op = 0, on = 0,ap = 0,an = 0,bp = 0,bn = 0, abp = 0, abn = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) { ;
                                String blood = document.getString("BloodGroup");
                                switch(blood){
                                    case "O+":
                                        op++;
                                    break;
                                }
                                switch(blood){
                                    case "O-":
                                        on++;
                                        break;
                                }
                                switch(blood){
                                    case "A+":
                                        ap++;
                                        break;

                                }
                                switch(blood){
                                    case "A-":
                                        an++;
                                        break;
                                }
                                switch(blood){
                                    case "B+":
                                        bp++;
                                        break;
                                }
                                switch(blood){
                                    case "B-":
                                        bn++;
                                        break;
                                }
                                switch(blood){
                                    case "AB+":
                                        abp++;
                                        break;
                                }
                                switch(blood){
                                    case "AB-":
                                        abn++;
                                        break;
                                }
                            }

                            o1.setText(op+"");
                            o2.setText(on+"");

                            a1.setText(ap+"");
                            a2.setText(an+"");

                            b1.setText(bp+"");
                            b2.setText(bn+"");

                            ab1.setText(abp+"");
                            ab2.setText(abn+"");
                            Toast.makeText(getApplicationContext(), "Done." , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void all(View view) {
        Intent i = new Intent(viewall.this, ReceiveBlood.class);
        startActivity(i);
        finish();
    }
}