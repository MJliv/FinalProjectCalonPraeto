package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {

    // input, button to regist, link login
    EditText usernameField, passField, emailField, confPassField;
    TextView linkLogin;
    Button registerButton;

    // sharedpreference
    SharedPreferences sharedPref;
    // shared preferences name and key
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "pass";
    private static final String KEY_CONFPASS = "confpass";

    // firebase
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // get id
        usernameField = findViewById(R.id.et_username);
        passField = findViewById(R.id.et_pass2);
        emailField = findViewById(R.id.et_email2);
        confPassField = findViewById(R.id.et_confpass);
        registerButton = findViewById(R.id.btn_register);
        linkLogin = findViewById(R.id.tv_login);

        // link back to login
        linkLogin.setOnClickListener(v -> {
            Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(loginIntent);
        });

        // sharepreference
        sharedPref = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        //auth
        auth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(v -> {
            // get data from EditText into String variables
            final String username = usernameField.getText().toString();
            final String confPass = confPassField.getText().toString();
            final String email = emailField.getText().toString().trim();
            final String pass = passField.getText().toString().trim();

            if(username.length() < 5){
                Toast.makeText(this, "Username must be longer than 5 character", Toast.LENGTH_SHORT).show();
            }else if(!emailField.getText().toString().contains("@") || !emailField.getText().toString().contains(".com")){
                Toast.makeText(this, "Email must contain @ and .com", Toast.LENGTH_SHORT).show();
            }else if(!passField.getText().toString().equals(confPass)){
                Toast.makeText(this, "Password must be matched with configuration pass", Toast.LENGTH_SHORT).show();
            }else if(username.isEmpty() || emailField.getText().toString().isEmpty() || passField.getText().toString().isEmpty() || confPass.isEmpty()){
                Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }else{
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();

                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(KEY_NAME, usernameField.getText().toString());
                            editor.putString(KEY_EMAIL, emailField.getText().toString());
                            editor.putString(KEY_PASS, passField.getText().toString());
                            editor.putString(KEY_CONFPASS, confPassField.getText().toString());
                            editor.apply();

                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}