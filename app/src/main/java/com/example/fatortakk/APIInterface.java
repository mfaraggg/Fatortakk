package com.example.fatortakk;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface
{
    @GET("allUserReceipts")
    Call<ArrayList<Receipt>> getReceipts(@Query ("userID") int userID);

    @GET("InsightPercentages")
    Call<String> getInsights(@Query("userID") int userID, @Query("category") String category);

    @GET("UserTotal")
    Call<String> getUserTotal(@Query("userID") int userID);

    @GET("allReceiptItems")
    Call<ArrayList<Item>> getItems(@Query("receiptID") int receiptID);

    @GET("getBonus")
    Call<String> getRewards(@Query("userID") int userID, @Query("name") String storeName);
}




