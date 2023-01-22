package com.example.fatortakk;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        String passedUserID = intent2.getStringExtra("userID");
        int userID = Integer.valueOf(passedUserID);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        APIInterface myAPI;
        Retrofit retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        myAPI = retrofit1.create(APIInterface.class);
        final String[] FinalTotal = new String[1];

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

//        Call<UserId> call2 = retrofitInterface.getUserByUsername(map);
//        call2.enqueue(new Callback<UserId>() {
//            @Override
//            public void onResponse(Call<UserId> call, Response<UserId> response) {
//                responseID = response.body().getId();
//                Toast.makeText(MainActivity2.this, responseID.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<UserId> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),
//                        t.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        });

        Call<String> arrayListCall = myAPI.getUserTotal(userID);
        arrayListCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    FinalTotal[0] = response.body();
                    Log.d("xxxxxxxxxUSERTOTAL:", FinalTotal[0]);
                } else {
                    Log.d("xxxxxxxxxxx", "response body is null");
                    Log.d("xxxxxxxxxxx", "response code: " + response.code());
                    Log.d("xxxxxxxxxxx", "response message: " + response.message());
                    Log.d("xxxxxxxxxxx", "response: " + response);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("xxxxxxxxxxx", "RESPONSE FAILED");
                Log.d("xxxxxxxxxxx", "error: " + t.getMessage());
                Toast.makeText(MainActivity2.this, "Failed to load user total.", Toast.LENGTH_LONG).show();
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
                intent.putExtra("userID", passedUserID);
                startActivity(intent);
            }
        });

        Insights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, Insights.class);
                intent.putExtra("userID", passedUserID);
                intent.putExtra("FinalTotal", FinalTotal[0]);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
