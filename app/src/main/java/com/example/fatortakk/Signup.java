package com.example.fatortakk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Button signupButton;
        signupButton = (Button) findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                HashMap<String, String> map = new HashMap<>();

                map.put("name", ((EditText) findViewById(R.id.editTextName)).getText().toString());
                map.put("email", ((EditText) findViewById(R.id.editTextEmail)).getText().toString());
                map.put("username", ((EditText) findViewById(R.id.editTextUsername)).getText().toString());
                map.put("password", ((EditText) findViewById(R.id.editTextPassword)).getText().toString());
                map.put("phone", ((EditText) findViewById(R.id.editTextPhone)).getText().toString());

                Call<Void> call = retrofitInterface.executeSignup(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        if (response.code() == 500) {
                            Toast.makeText(Signup.this, "Signup Failed", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Signup.this, "Signup Successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Signup.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Signup.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
//
//                String username, password, email, phone, name;
//                username = ((EditText) findViewById(R.id.editTextUsername)).getText().toString();
//                password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
//                email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
//                phone = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
//                name = ((EditText) findViewById(R.id.editTextName)).getText().toString();
//
//                if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty() || name.isEmpty()) {
//                    Toast toast = Toast.makeText(Signup.this, "All fields are required", Toast.LENGTH_SHORT);
//                    toast.show();
//                }
//                else{
//                    Intent intent = new Intent(Signup.this, MainActivity2.class);
//                    startActivity(intent);
//                }
            }
        });

    }
}