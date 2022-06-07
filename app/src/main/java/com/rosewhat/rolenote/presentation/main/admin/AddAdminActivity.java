package com.rosewhat.rolenote.presentation.main.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.rosewhat.rolenote.R;
import com.rosewhat.rolenote.domain.User;

public class AddAdminActivity extends AppCompatActivity {

    private Spinner spinnerMatch;
    private EditText editTextComment;
    private Spinner spinnerTribuna;
    private EditText editTextPlace;

    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        spinnerMatch = findViewById(R.id.spinnerMatch);
        editTextComment = findViewById(R.id.editTextComment);
        spinnerTribuna = findViewById(R.id.spinnerTribuna);
        editTextPlace = findViewById(R.id.editTextPlace);
        buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String match = spinnerMatch.getSelectedItem().toString();
                String comment = editTextComment.getText().toString().trim();
                String tribuna = spinnerTribuna.getSelectedItem().toString();
                String place = editTextPlace.getText().toString().trim();

                User user = new User(match, comment, tribuna, place);
                AdminActivity.usersList.add(user);

                Intent intent = new Intent(AddAdminActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }
}