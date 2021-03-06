package com.rosewhat.rolenote.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rosewhat.rolenote.R;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();



        if (mAuth.getCurrentUser() != null) {

        } else {
            signOut();
        }
    }

    private void signOut() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}