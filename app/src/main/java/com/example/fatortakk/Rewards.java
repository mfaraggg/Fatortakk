package com.example.fatortakk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rewards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewardss);

        Intent intentInsights = getIntent();
        String userID = intentInsights.getStringExtra("userID");
        int passedUserID = Integer.valueOf(userID);

        String BaseURL = "http://10.0.2.2:3000/";
        Resources res = getResources();
        String[] StoreNames = res.getStringArray(R.array.StoresArray);

        APIInterface myAPI;
        ListView RewardList = findViewById(R.id.AllRewards);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        myAPI = retrofit.create(APIInterface.class);

        int [] logoImages = {R.drawable.copa_acai, R.drawable.gourmet, R.drawable.ikea, R.drawable.nike,
                R.drawable.ovio, R.drawable.starbucks, R.drawable.zara};

        final HashMap<String, String> StoreRewards = new HashMap<>();
        for (int i=0; i<StoreNames.length; i++)
        {
            Call<String> arrayListCall = myAPI.getRewards(passedUserID, StoreNames[i]);
            final int finalI = i;

            arrayListCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful())
                    {
                        StoreRewards.put(StoreNames[finalI], response.body());
                        RewardsAdapter rewardsAdapter = new RewardsAdapter(Rewards.this, logoImages,
                                StoreNames, StoreRewards);
                        RewardList.setAdapter(rewardsAdapter);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(Rewards.this, "Failed to load bonuses.", Toast.LENGTH_LONG).show();
                }
            });

        }

    }
}