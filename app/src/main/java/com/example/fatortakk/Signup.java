package com.example.fatortakk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button signupButton;
        signupButton = (Button) findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String username, password, email, phone, name;
                username = ((EditText) findViewById(R.id.editTextUsername)).getText().toString();
                password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
                email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
                phone = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
                name = ((EditText) findViewById(R.id.editTextName)).getText().toString();

                if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty() || name.isEmpty()) {
                    Toast toast = Toast.makeText(Signup.this, "All fields are required", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    Intent intent = new Intent(Signup.this, MainActivity2.class);
                    startActivity(intent);
                }
            }
        });

    }
}