package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText txtemail,txtpassword;
    TextView login,createto;
    Button btnlogin;
    FirebaseAuth fAuth;
    FloatingActionButton fbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtemail= findViewById(R.id.email);
        txtpassword=findViewById(R.id.password);
        createto=findViewById(R.id.textcreate);
        btnlogin=findViewById(R.id.login);
        login=findViewById(R.id.textlogin);
        fAuth= FirebaseAuth.getInstance();
        fbutton=findViewById(R.id.floating2);

        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Signup.class));
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtemail.getText().toString().trim();
                String password  = txtpassword.getText().toString().trim();

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

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(Login.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                        }
                        else {
                            Toast.makeText(Login.this, "Error." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        createto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
            }
        });
    }
}