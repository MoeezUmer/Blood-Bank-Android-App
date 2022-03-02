package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ReceiveBlood extends AppCompatActivity{

    private static final String TAG = "Hello";
    FirebaseFirestore fstore;
    private DatabaseReference db;

    private Button search;
    private Spinner spinner;
    private ArrayAdapter adapter;
    //RecyclerView recycle;
    //ArrayList<Data> userarray;
    //MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_blood);

       // recycle = (RecyclerView)findViewById(R.id.donorid);
        search = (Button) findViewById(R.id.search);
        spinner = (Spinner) findViewById(R.id.BloodGroup);
        fstore=FirebaseFirestore.getInstance();

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
       // recycle.setLayoutManager(linearLayoutManager);
       // recycle.setHasFixedSize(true);

       // userarray=new ArrayList<Data>();
       // adapter=new MyAdapter(ReceiveBlood.this,userarray);
       // recycle.setAdapter(adapter);





        adapter = new ArrayAdapter<String>(this, R.layout.activity_listview);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
        //readChanges();
    }

    public void search(View view) {
        searchFunction();
    }

    public void all(View view) {
        Intent i = new Intent(ReceiveBlood.this, viewall.class);
        startActivity(i);
        finish();
    }

    public void  searchFunction(){

        String spin = spinner.getSelectedItem().toString();

        Toast.makeText(getApplicationContext(), "Loading...." , Toast.LENGTH_SHORT).show();

        fstore.collection("donors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        String myText = "";
                        adapter.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) { ;
                                String blood = document.getString("BloodGroup");
                                if(blood.equals(spin)){
                                    String name = document.getString("Fullname");
                                    String phone = document.getString("PhoneNo");
                                    String address = document.getString("Address");

                                    String city = document.getString("City");
                                    myText = "Fullname: "+name+"\nAddress: "+address+"\nBloodGroup: "+blood+"\nPhoneNumber: "+phone+"\nCity: "+city;
                                    adapter.add(myText);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            myText = "No Data About This BloodGroup";
                            adapter.add(myText);
                        }
                    }
                });
    }
}

