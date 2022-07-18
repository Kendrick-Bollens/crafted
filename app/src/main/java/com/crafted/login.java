package com.crafted;

import android.os.Bundle;

import com.crafted.external.Cognito;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class login extends AppCompatActivity {
    // ############################################################# View Components
    TextView txtForgetPass;     // For retrieving password
    Button btnLogin;            // Button for Login
    Button btnTestLogin;
    TextInputLayout etUsername;
    TextInputLayout etPassword;
    TextView txtKontoErstellen;
    // ############################################################# End View Components

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        initViewComponents();

    }

    private void initViewComponents(){
        txtForgetPass= findViewById(R.id.txtForgetPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnTestLogin = findViewById(R.id.btnTestLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        txtKontoErstellen = findViewById(R.id.login_konto_erstellen);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cognito authentication = new Cognito(getApplicationContext());
                authentication.userLogin(etUsername.getEditText().getText().toString().replace(" ", ""), etPassword.getEditText().getText().toString());

            }
        });

        btnTestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cognito authentication = new Cognito(getApplicationContext());
                authentication.userLogin("Julia", "Test123!");

            }
        });

        txtForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                builder.setMessage("Noch nicht implementiert");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        txtKontoErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                builder.setMessage("Noch nicht implementiert");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }




}