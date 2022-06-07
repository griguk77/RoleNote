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
import com.rosewhat.rolenote.presentation.main.user.UserActivity;

import org.jetbrains.annotations.NotNull;

public class UserLoginActivity extends AppCompatActivity {

    private TextView textViewRegister3;
    private TextView textViewLoginAdmin3;
    private TextView textViewLoginUser3;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLoginUser;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextEmailUserLogin);
        editTextPassword = findViewById(R.id.editTextPasswordUserLogin);
        buttonLoginUser = findViewById(R.id.buttonLoginUser);



        textViewRegister3 = findViewById(R.id.textViewRegister3);
        textViewLoginAdmin3 = findViewById(R.id.textViewLoginAdmin3);
        textViewLoginUser3 = findViewById(R.id.textViewLoginUser3);

        textViewRegister3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        textViewLoginAdmin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        textViewLoginUser3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });

        buttonLoginUser.setOnClickListener(new View.OnClickListener() {
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
                            Intent intent = new Intent(UserLoginActivity.this, UserActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(UserLoginActivity.this, "Ошибка " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}