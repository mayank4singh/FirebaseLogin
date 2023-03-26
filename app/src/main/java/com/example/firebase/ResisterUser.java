package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ResisterUser extends AppCompatActivity {
    EditText mail, pass;
    Button user, back;

    FirebaseAuth Fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resister_user);

        mail = findViewById(R.id.editTextTextEmailAddress2);
        pass = findViewById(R.id.editTextTextPassword2);
        user = findViewById(R.id.button);
        back = findViewById(R.id.button2);

        Fauth = FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String semail = mail.getText().toString();
                final String spass = pass.getText().toString();
                Fauth.createUserWithEmailAndPassword(semail,spass).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(ResisterUser.this, "USER HAS BEEN CREATED", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ResisterUser.this, "SOMETHING WENT WRONG", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}