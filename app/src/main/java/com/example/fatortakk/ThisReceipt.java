package com.example.fatortakk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThisReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_receipt);

        Intent intent = getIntent();
        String receiptID = intent.getStringExtra("selectedReceipt");
        String storeName = intent.getStringExtra("selectedStore");
        String receiptDate = intent.getStringExtra("receiptDate");
        String receiptTime = intent.getStringExtra("receiptTime");
        String receiptTotal = intent.getStringExtra("receiptTotal");
        Resources res = getResources();
        String [] StoreNames = res.getStringArray(R.array.StoresArray);
        int [] logoImages = {R.drawable.copa_acai, R.drawable.gourmet, R.drawable.ikea, R.drawable.nike,
                R.drawable.ovio, R.drawable.starbucks, R.drawable.zara};

        int RID = Integer.valueOf(receiptID);
        Float Total = Float.valueOf(receiptTotal);

        APIInterface myAPI;
        ListView ItemListView = findViewById(R.id.AllItems);
        String BaseURL = "http://10.0.2.2:3000/";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        myAPI = retrofit.create(APIInterface.class);
        ImageView Logo = findViewById(R.id.ReceiptLogo);
        TextView Store = findViewById(R.id.StoreName);
        TextView Date = findViewById(R.id.ReceiptDate);
        TextView Time = findViewById(R.id.ReceiptTime);
        TextView TotalN = findViewById(R.id.TotalNum);
        boolean LogoFound = false;

        Call<ArrayList<Item>> arrayListCall = myAPI.getItems(RID);

        for (int i=0; i<StoreNames.length;i++)
        {
            if (storeName.equals(StoreNames[i]))
            {
                Logo.setImageResource(logoImages[i]);
                LogoFound = true;
            }
        }

        if (LogoFound == false)
            Logo.setImageResource(R.drawable.default_logo);

        Store.setText(storeName);
        Date.setText(receiptDate);
        Time.setText(receiptTime);
        TotalN.setText(receiptTotal);




        arrayListCall.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Item> ReceiptItems = response.body();
                    if (ReceiptItems!= null)
                    {
                        SingleReceiptAdapter ThisReceiptAdapter = new SingleReceiptAdapter(ReceiptItems, ThisReceipt.this,
                                R.layout.singleitem);
                        ItemListView.setAdapter(ThisReceiptAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
                Toast.makeText(ThisReceipt.this, "Failed to load receipt.", Toast.LENGTH_LONG).show();
            }
        });







    }
}














