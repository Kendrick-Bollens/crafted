package com.example.crafted;

import android.os.Bundle;

import com.example.crafted.auth.Cognito;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.crafted.databinding.ActivityLoginBinding;

import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    // ############################################################# View Components
    //TextView txtForgetPass;     // For retrieving password
    Button btnLogin;            // Button for Login
    EditText etUsername;
    EditText etPassword;
    // ############################################################# End View Components

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        initViewComponents();
    }

    private void initViewComponents(){
        //txtForgetPass= findViewById(R.id.txtForgetPass);
        btnLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cognito authentication = new Cognito(getApplicationContext());
                authentication.userLogin(etUsername.getText().toString().replace(" ", ""), etPassword.getText().toString());

            }
        });
    }




}