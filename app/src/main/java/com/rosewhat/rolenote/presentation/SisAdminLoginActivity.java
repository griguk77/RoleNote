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
import com.rosewhat.rolenote.presentation.main.sisadmin.SisAdminActivity;

import org.jetbrains.annotations.NotNull;

public class SisAdminLoginActivity extends AppCompatActivity {

    private TextView textViewRegister2;
    private TextView textViewLoginAdmin2;
    private TextView textViewLoginUser3;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLoginSisAdmin;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sis_admin_login);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextEmailSisAdminLogin);
        editTextPassword = findViewById(R.id.editTextPasswordSisAdminLogin);
        buttonLoginSisAdmin = findViewById(R.id.buttonLoginSisAdmin);

        textViewRegister2 = findViewById(R.id.textViewRegister2);
        textViewLoginAdmin2 = findViewById(R.id.textViewLoginAdmin2);
        textViewLoginUser3 = findViewById(R.id.textViewLoginUser3);

        textViewRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SisAdminLoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        textViewLoginAdmin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SisAdminLoginActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        textViewLoginUser3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SisAdminLoginActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });

        buttonLoginSisAdmin.setOnClickListener(new View.OnClickListener() {
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
                            Intent intent = new Intent(SisAdminLoginActivity.this, SisAdminActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SisAdminLoginActivity.this, "Ошибка " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}