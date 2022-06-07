package com.rosewhat.rolenote.presentation.main.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rosewhat.rolenote.R;
import com.rosewhat.rolenote.domain.User;
import com.rosewhat.rolenote.presentation.RegisterActivity;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    public static final ArrayList<User> usersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Button button2;
    private AdminAdapter adapter;

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
        setContentView(R.layout.activity_admin);

        button2 = findViewById(R.id.button2);
        recyclerView = findViewById(R.id.recyclerViewAdmin);
        adapter = new AdminAdapter(usersList, this);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AddAdminActivity.class);
                startActivity(intent);
            }
        });


        adapter.setOnNoteClickListener(new AdminAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(AdminActivity.this, "Билет №" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                remove(position);
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
