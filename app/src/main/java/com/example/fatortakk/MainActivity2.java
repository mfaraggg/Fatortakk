package com.example.fatortakk;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    ImageButton AllReceipts, Insights, MyAccount, PersonalQR;
    Integer responseID;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView editTextHello = (TextView) findViewById(R.id.HelloTV);

        Intent intent2 = getIntent();
        String passedUsername = intent2.getStringExtra("id");

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("username", passedUsername);

        Call<UserId> call = retrofitInterface.getNameByUsername(map);

        call.enqueue(new Callback<UserId>() {
            @Override
            public void onResponse(Call<UserId> call, Response<UserId> response) {
                String responseName = response.body().getName();
                editTextHello.setText("Hello, " + responseName);
            }

            @Override
            public void onFailure(Call<UserId> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        Call<UserId> call2 = retrofitInterface.getUserByUsername(map);
        call2.enqueue(new Callback<UserId>() {
            @Override
            public void onResponse(Call<UserId> call, Response<UserId> response) {
                responseID = response.body().getId();
                Toast.makeText(MainActivity2.this, responseID.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserId> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton AllReceipts, Insights, MyAccount;
        ImageButton PersonalQR;

        PersonalQR = (ImageButton) findViewById(R.id.button2);
        MyAccount = (ImageButton) findViewById(R.id.button4);
        AllReceipts = (ImageButton) findViewById(R.id.button3);
        Insights = (ImageButton) findViewById(R.id.button1);
        PersonalQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("id", passedUsername);
                startActivity(intent);
            }
        });

        MyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity2.this, MyAccount.class);
                intent.putExtra("id", passedUsername);
                startActivity(intent);
            }
        });

        AllReceipts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, AllReceipts.class);
                intent.putExtra("id", passedUsername);
                intent.putExtra("userID", responseID.toString());
                startActivity(intent);
            }
        });

        Insights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
