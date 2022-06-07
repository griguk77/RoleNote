package com.rosewhat.rolenote.presentation.main.sisadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rosewhat.rolenote.R;
import com.rosewhat.rolenote.domain.User;
import com.rosewhat.rolenote.presentation.RegisterActivity;

import java.util.ArrayList;

public class SisAdminActivity extends AppCompatActivity {

    public static final ArrayList<User> usersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Button button3;
    private SisAdminAdapter adapter;
    private Button buttonBuy;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemSignOut) {
            mAuth.signOut();
            signOut();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sis_admin);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();



        button3 = findViewById(R.id.button3);
        recyclerView = findViewById(R.id.recyclerViewSisAdmin);
        adapter = new SisAdminAdapter(usersList, this);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SisAdminActivity.this, AddSisAdminActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    private void remove(int position) {
        usersList.remove(position);
        adapter.notifyDataSetChanged();
    }

    private void signOut() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}