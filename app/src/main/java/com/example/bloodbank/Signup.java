package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText txtfullname,txtemail,txtusername,txtpassword;
    Button btnsignup;
    FirebaseAuth fAuth;
    TextView move;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txtfullname=findViewById(R.id.fullname);
        txtemail=findViewById(R.id.email);
        txtusername=findViewById(R.id.username);
        txtpassword=findViewById(R.id.password);
        btnsignup=findViewById(R.id.signup);
        move=findViewById(R.id.textmovetologin);

        fAuth= FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();


        if (fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtemail.getText().toString().trim();
                String password  = txtpassword.getText().toString().trim();
                String fullName=txtfullname.getText().toString();
                String userName=txtusername.getText().toString();

                if (TextUtils.isEmpty(email)){
                    txtemail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    txtpassword.setError("Password is Required");
                    return;
                }
                if (password.length() < 6){
                    txtpassword.setError("Password must b >= 6 Characters.");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(Signup.this, "User Created .", Toast.LENGTH_SHORT).show();
                           userID = fAuth.getCurrentUser().getUid();
                           DocumentReference documentReference= fStore.collection("users").document(userID);
                           Map<String,Object> user = new HashMap<>();
                           user.put("Fullname",fullName);
                           user.put("Email",email);
                           user.put("Username",userName);
                           documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void aVoid) {
                                   Log.d(TAG,"onSuccess: User profile is created for"+userID);
                               }
                           });
                           startActivity(new Intent(getApplicationContext(),MainActivity.class));
                       }
                       else {
                           Toast.makeText(Signup.this, "Error." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }
                    }
                });
            }
        });

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}