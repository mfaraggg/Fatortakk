package com.example.fatortakk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Insights extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insights);

        Intent intentInsights = getIntent();
        String userID = intentInsights.getStringExtra("userID");
        int passedUserID = Integer.valueOf(userID);

        String BaseURL = "http://10.0.2.2:3000/";
        Resources res = getResources();
        String[] Categories = res.getStringArray(R.array.Categories);
        String FinalTotal = intentInsights.getStringExtra("FinalTotal");

        APIInterface myAPI;
        ListView InsightList = findViewById(R.id.AllInsights);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        myAPI = retrofit.create(APIInterface.class);

        final HashMap<String, String> categoryTotals = new HashMap<>();

        for (int i = 0; i < Categories.length; i++) {
            Call<String> arrayListCall = myAPI.getInsights(passedUserID, Categories[i]);
            final int finalI = i;
            arrayListCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        categoryTotals.put(Categories[finalI], response.body());
                        Log.d(Categories[finalI]+": ", categoryTotals.get(Categories[finalI]));
                        InsightsAdapter insightsAdapter = new InsightsAdapter(Insights.this, categoryTotals, FinalTotal, Categories);
                        InsightList.setAdapter(insightsAdapter);
                    } else {
                        Log.d("NULL RESPONSE", "Response body is EMPTY");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(Insights.this, "Failed to load category total.", Toast.LENGTH_LONG).show();
                }
            });
        }


    }
}