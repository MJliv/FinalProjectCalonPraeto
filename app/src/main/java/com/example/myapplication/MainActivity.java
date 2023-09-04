package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    // input, button, link regis
    EditText emailField, passField;
    Button loginButton;
    TextView regisLink;

    //sharepreferences to match with sharepreferences data from regis
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "pass";
    private static final String KEY_CONFPASS = "confpass";

    // auth
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // input, button, link regis
        emailField = findViewById(R.id.et_email);
        passField = findViewById(R.id.et_pass);
        loginButton = findViewById(R.id.btn_login);

        // regis intent
        regisLink = findViewById(R.id.tv_regis);
        regisLink.setOnClickListener(v -> {
            Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });

//        if(name != null || email != null || pass!= null || confpass != null){
//            // if data is available so directly call on HomeActivity
//            Intent intent = new Intent(MainActivity.this, profileActivity.class);
//            startActivity(intent);
//        }

        // sharedpreferences
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        // auth
        auth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailSP = sharedPreferences.getString(KEY_EMAIL, null);
                String passSP = sharedPreferences.getString(KEY_PASS, null);

                String email = emailField.getText().toString();
                String pass = passField.getText().toString();

                if(!email.equals(emailSP)){
                    Toast.makeText(MainActivity.this, "Email doesn't exist", Toast.LENGTH_SHORT).show();
                }else if(!pass.equals(passSP)){
                    Toast.makeText(MainActivity.this, "Password incorrect", Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(MainActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                            homeIntent.putExtra(SHARED_PREF_NAME, sharedPreferences.getString(SHARED_PREF_NAME, ""));
                            startActivity(homeIntent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }



}