package com.example.fatortakk;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface
{
    @GET("allreceipts")
    Call<ArrayList<Receipt>> getReceipts(@Query ("userID") int userID);
}




