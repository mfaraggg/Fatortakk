package com.example.fatortakk;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
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

        Intent intentReceipts = getIntent();
        String userID = intentReceipts.getStringExtra("userID");

        APIInterface myAPI;
        ListView ReceiptListView = findViewById(R.id.AllReceipts);
        String BaseURL = "http://10.0.2.2:3000/";

        int passedUserID = Integer.valueOf(userID);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        myAPI = retrofit.create(APIInterface.class);

        Resources res = getResources();
        int [] logoImages = {R.drawable.copa_acai, R.drawable.gourmet, R.drawable.ikea, R.drawable.nike,
                R.drawable.ovio, R.drawable.starbucks, R.drawable.zara};
        String[] storeNames = res.getStringArray(R.array.StoresArray);

        Log.d("xxxxxxUSERID", userID);

        Call<ArrayList<Receipt>> arrayListCall = myAPI.getReceipts(passedUserID);

        arrayListCall.enqueue(new Callback<ArrayList<Receipt>>() {
            @Override
            public void onResponse(Call<ArrayList<Receipt>> call, Response<ArrayList<Receipt>> response) {
                if (response.isSuccessful()) {
                    Log.d("xxxxxxxxxxx", "RESPONSE SUCCESSFUL");
                    Log.d("xxxxxxxxxxx", "response body: " + response.body());
                    ArrayList<Receipt> receiptList = response.body();
                    if (receiptList != null) {
                        ReceiptAdapter adapter = new ReceiptAdapter(receiptList, AllReceipts.this, R.layout.singlereceipt,
                                storeNames,logoImages);
                        ReceiptListView.setAdapter(adapter);
                        ReceiptListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(AllReceipts.this, ThisReceipt.class);
                                startActivity(intent);
                            }
                        });
                    } else {
                        Log.d("xxxxxxxxxxx", "response body is null");
                        Log.d("xxxxxxxxxxx", "response code: " + response.code());
                        Log.d("xxxxxxxxxxx", "response message: " + response.message());
                        Log.d("xxxxxxxxxxx", "response: " + response);

                    }
                } else {
                    Log.d("xxxxxxxxxxx", "RESPONSE FAILED");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Receipt>> call, Throwable t) {
                Log.d("xxxxxxxxxxx", "RESPONSE FAILED");
                Log.d("xxxxxxxxxxx", "error: " + t.getMessage());
                Toast.makeText(AllReceipts.this, "Failed to load receipts.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
