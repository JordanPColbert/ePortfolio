package com.zybooks.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    private Button logButton;
    private Button registerButton;
    private EditText usr;
    private EditText pswrd;

    // Defines layout on creation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        logButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.newUserButton);
        usr = findViewById(R.id.username);
        pswrd = findViewById(R.id.password);
    }

    // Login button will check for correct info, otherwise nothing happens
    public void onLoginClick(View view) {
        String uTxt = usr.getText().toString();
        String pTxt = pswrd.getText().toString();

        DatabaseHelper helper = new DatabaseHelper(LogIn.this);

        boolean b = helper.checkLogIn(uTxt, pTxt);

        // If credentials match, main screen appears
        if (b) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(LogIn.this, "Login successful", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(LogIn.this, "Incorrect username/password", Toast.LENGTH_LONG).show();
        }
    }

    // Register user, with no restrictions on name/password as of yet
    public void onRegisterClick(View view) {
        String usrTxt = usr.getText().toString();
        String pswrdTxt = pswrd.getText().toString();

        DatabaseHelper databaseHelper = new DatabaseHelper(LogIn.this);

        boolean b = databaseHelper.addUser(usrTxt, pswrdTxt);
        if (b) {
            Toast.makeText(LogIn.this, "Successful addition of user " + usrTxt, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(LogIn.this, "Error when adding user. Entry already exists.", Toast.LENGTH_LONG).show();
        }


    }
}