package com.example.fatortakk;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String,String> map);

    @POST("/signup")
    Call<Void> executeSignup(@Body HashMap<String,String> map);

    @GET("/getuser")
    Call<LoginResult> getUser(@Body HashMap<String,String> map);

    @POST("/getNameByUsername")
    Call<UserId> getNameByUsername(@Body HashMap<String,String> map);

    @POST("/getUserByUsername")
    Call<UserId> getUserByUsername(@Body HashMap<String,String> map);

    @POST("/updatePasswordByUsername")
    Call<Void> updatePasswordByUsername(@Body HashMap<String,String> map);

    @POST("/updateNameByUsername")
    Call<Void> updateNameByUsername(@Body HashMap<String,String> map);

    @POST("/updateNameAndPasswordByUsername")
    Call<Void> updateNameAndPasswordByUsername(@Body HashMap<String,String> map);

    @FormUrlEncoded
    @POST("/getUserId")
    Call<UserId> getUserId(@FieldMap HashMap<String, String> params);

    @POST("/sendRecoveryEmail")
    Call<UserId> sendRecoveryEmail(@Body HashMap<String,String> map);



}
