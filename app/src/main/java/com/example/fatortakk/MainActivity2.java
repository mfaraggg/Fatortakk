package com.example.fatortakk;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

        ImageView AllReceipts, Insights, MyAccount, PersonalQR;
        TextView AllReceiptsTV, InsightsTV, MyAccountTV, PersonalQRTV;

        PersonalQR = (ImageView) findViewById(R.id.myqrIV);
        MyAccount = (ImageView) findViewById(R.id.myaccountIV);
        AllReceipts = (ImageView) findViewById(R.id.myreceiptsIV);
        Insights = (ImageView) findViewById(R.id.myinsightsIV);

        PersonalQRTV = (TextView) findViewById(R.id.myqrTV);
        MyAccountTV = (TextView) findViewById(R.id.NameTV);
        AllReceiptsTV = (TextView) findViewById(R.id.myreceiptsTV);
        InsightsTV = (TextView) findViewById(R.id.myinsightsTV);

        Intent intent2 = getIntent();
        String passedUsername = intent2.getStringExtra("id");
        String passedUserID = intent2.getStringExtra("userID");

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
                MyAccountTV.setText(responseName);
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
        PersonalQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("id", passedUsername);
                startActivity(intent);
            }
        });

        PersonalQRTV.setOnClickListener(new View.OnClickListener() {
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

        MyAccountTV.setOnClickListener(new View.OnClickListener() {
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

        AllReceiptsTV.setOnClickListener(new View.OnClickListener() {
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
                startActivity(intent);
            }
        });

        InsightsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, Insights.class);
                intent.putExtra("userID", passedUserID);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
