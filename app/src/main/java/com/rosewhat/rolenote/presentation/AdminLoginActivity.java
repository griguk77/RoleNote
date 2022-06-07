package com.rosewhat.rolenote.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rosewhat.rolenote.R;
import com.rosewhat.rolenote.presentation.main.admin.AdminActivity;

import org.jetbrains.annotations.NotNull;

public class AdminLoginActivity extends AppCompatActivity {

    private TextView textViewRegister;
    private TextView textViewLoginSisAdmin2;
    private TextView textViewLoginUser2;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private Button buttonLoginAdmin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextEmailAdminLogin);
        editTextPassword = findViewById(R.id.editTextPasswordAdminLogin);

        buttonLoginAdmin = findViewById(R.id.buttonLoginAdmin);
        textViewRegister = findViewById(R.id.textViewRegister);
        textViewLoginSisAdmin2 = findViewById(R.id.textViewLoginSisAdmin2);
        textViewLoginUser2 = findViewById(R.id.textViewLoginUser2);

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        textViewLoginSisAdmin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this, SisAdminLoginActivity.class);
                startActivity(intent);
            }
        });

        textViewLoginUser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });

        buttonLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(AdminLoginActivity.this, AdminActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(AdminLoginActivity.this, "Ошибка " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}