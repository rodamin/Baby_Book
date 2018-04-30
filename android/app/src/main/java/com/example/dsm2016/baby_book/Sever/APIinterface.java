package com.example.dsm2016.baby_book.Sever;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ghdth on 2018-04-24.
 */

public interface APIinterface  {
    String URL="http://10.156.145.194:3000/";
    @FormUrlEncoded
    @POST("/join")
    Call<Void> join(@Field("id")String id,
                    @Field("password")String password,
                    @Field("name")String name,
                    @Field("email")String email ,
                    @Field("gender")int gender);
    @FormUrlEncoded
    @POST("/conjoin")
    Call<Void> conjoin(@Field("id")String id,
                    @Field("password")String password,
                    @Field("name")String name,
                    @Field("email")String email ,
                    @Field("gender")int gender,
                    @Field("other_id") String other_id);
    @FormUrlEncoded
    @POST("/login")
    Call<JsonObject> login(@Field("id") String id,
                     @Field("password")String password);

}
