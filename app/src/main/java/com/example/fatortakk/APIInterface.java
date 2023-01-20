package com.example.fatortakk;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface
{
    @GET("allreceipts")
    Call<ArrayList<Receipt>> getReceipts();
}




