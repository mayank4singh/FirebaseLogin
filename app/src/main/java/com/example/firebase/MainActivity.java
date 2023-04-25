 package com.example.firebase;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

 public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button login, resisters;
    FirebaseAuth Auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.loginbutton);
        resisters = findViewById(R.id.resister);

        Auth = FirebaseAuth.getInstance();

        resisters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResisterUser.class);
                startActivity(intent);
            }
        });

        if(Auth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            finish();

        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String meamil = email.getText().toString();
                final String mpass = password.getText().toString();

              Auth.signInWithEmailAndPassword(meamil,mpass).addOnCompleteListener(task -> {
                  if(task.isSuccessful()){
                      Toast.makeText(MainActivity.this, "logged in successful", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(MainActivity.this, Dashboard.class);
                      startActivity(intent);
                  }else{
                      Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                  }
              });
            }
        });


    }
}