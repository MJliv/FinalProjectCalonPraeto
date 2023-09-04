package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class profileActivity extends AppCompatActivity {

    TextView nameField, emailField;
    Button logoutButton, backButton;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME= "name";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameField = findViewById(R.id.tv_name);
        emailField = findViewById(R.id.tv_email);
        logoutButton = findViewById(R.id.btn_logout);
        backButton = findViewById(R.id.btn_back);

        // display name and email from sharepreferences
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        if(name!= null || email != null){
            nameField.setText("Name - " + name);
            emailField.setText("Email - " + email);
        }

        // back button
        backButton.setOnClickListener(v -> {
            Intent i = new Intent(profileActivity.this, HomeActivity.class);
            startActivity(i);
        });

        // call button for log out
        logoutButton.setOnClickListener( v -> {
            Toast.makeText(profileActivity.this, "Log out succesfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(profileActivity.this, MainActivity.class);
            startActivity(i);
        });

    }
}