package com.example.dsm2016.baby_book.Sever;

import retrofit2.Call;
import com.google.gson.JsonObject;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ghdth on 2018-04-24.
 */

public interface APIinterface  {

    String URL="http://10.156.145.193:3000/";
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

    @FormUrlEncoded
    @POST("/baby/store")
    Call<Void> baby(@Field("baby_name") String baby_name,
                    @Field("gender") int gender,
                    @Field("birth") Date birth);

    @FormUrlEncoded
    @POST("/baby/update")
    Call<Void> baby(@Field("new_baby_name") String new_baby_name,
                    @Field("prev_baby_name") String prev_baby_name,
                    @Field("gender") int gender,
                    @Field("birth") Date birth);

    @GET("/growth/baby_name")
    Call<JsonArray> growth_graph(@Query("baby_name") String baby_name,
                                @Query("date") Date date,
                                @Query("weight") int weight,
                                @Query("height") int height);

}
