package com.example.fatortakk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAccount extends AppCompatActivity {

    private String UserName;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

        Intent intent3 = getIntent();
        UserName = intent3.getStringExtra("id");

        EditText editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        TextView editTextHello = (TextView) findViewById(R.id.fatortakTV);

        editTextUsername.setHint(UserName);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("username", UserName);

        Call<UserId> call = retrofitInterface.getUserByUsername(map);
//        Call<UserId> call = retrofitInterface.getNameByUsername(map);

        call.enqueue(new Callback<UserId>() {
            @Override
            public void onResponse(Call<UserId> call, Response<UserId> response) {
                if (response.code() == 200) {
                    String responseName = response.body().getName();
                    String responsePhone = response.body().getPhone();
                    String responseEmail = response.body().getEmail();
                    editTextHello.setText(responseName+"'s Profile");
                    editTextPhone.setHint(responsePhone);
                    editTextEmail.setHint(responseEmail);
                    Toast.makeText(MyAccount.this, "Success", Toast.LENGTH_LONG).show();

                } else if (response.code() == 400) {
                    Toast.makeText(MyAccount.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserId> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAccount.this.finish();
            }
        });

    }
}