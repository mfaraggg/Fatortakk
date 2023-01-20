package com.example.fatortakk;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllReceipts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_receipts);

        APIInterface myAPI;
        ListView ReceiptList;
        String BaseURL = "http://localhost:3000";

        ReceiptList = findViewById(R.id.AllReceipts);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        myAPI = retrofit.create(APIInterface.class);
        Call<ArrayList<Receipt>> arrayListCall = myAPI.getReceipts();
        arrayListCall.enqueue(new Callback<ArrayList<Receipt>>() {
            @Override
            public void onResponse(Call<ArrayList<Receipt>> call, Response<ArrayList<Receipt>> response) {
                if (response.isSuccessful())
                {
                     ArrayList<Receipt> ReceiptArrayList = new ArrayList<>();
                     for (int i=0; i<ReceiptArrayList.size(); i++)
                        {
                            ReceiptAdapter receiptAdapter = new ReceiptAdapter(ReceiptArrayList, AllReceipts.this, R.layout.singlereceipt);
                            ReceiptList.setAdapter(receiptAdapter);
                        }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Receipt>> call, Throwable t)
                {
                    Toast.makeText(AllReceipts.this, "Failed to load universities.", Toast.LENGTH_SHORT).show();
                }
        });




    }
}