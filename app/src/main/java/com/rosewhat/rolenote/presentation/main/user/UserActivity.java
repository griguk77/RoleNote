package com.rosewhat.rolenote.presentation.main.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

public class UserActivity extends AppCompatActivity {

    public static final ArrayList<User> usersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter adapter;

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
        setContentView(R.layout.activity_user);

        recyclerView = findViewById(R.id.recyclerViewUser);
        adapter = new UserAdapter(usersList);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        if(usersList != null) {
            usersList.add(new User("Цска - Спартак", "Победит Спартак", "Трибуна А", "1 место"));
            usersList.add(new User("Цска - Спартак", "Победит Спартак", "Трибуна А", "1 место"));
            usersList.add(new User("Цска - Спартак", "Победит Спартак", "Трибуна А", "1 место"));
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void signOut() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}