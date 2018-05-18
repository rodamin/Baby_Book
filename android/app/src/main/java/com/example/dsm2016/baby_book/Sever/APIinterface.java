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
import retrofit2.http.Path;
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
    Call<JsonArray> login(@Field("id") String id,
                          @Field("password")String password);

    @FormUrlEncoded
    @POST("/baby/store")
    Call<Void> baby(@Field("baby_name") String baby_name,
                    @Field("gender") int gender,
                    @Field("birth") String birth,
                    @Field("code") int code);

    @FormUrlEncoded
    @POST("/baby/update")
    Call<Void> baby_update(@Field("new_baby_name") String new_baby_name,
                           @Field("prev_baby_name") String prev_baby_name,
                           @Field("gender") int gender,
                           @Field("birth") String birth,
                           @Field("code") int code);

    @FormUrlEncoded
    @POST("/baby/delete")
    Call<Void> baby_delete(@Field("baby_name") String baby_name,
                           @Field("code") int code);

    @GET("/baby/load/{code}")
    Call<JsonArray> baby_call(@Path("code") int code);

    @FormUrlEncoded
    @POST("/growth/store")
    Call<Void> growth_store(@Field("baby_name") String baby_name,
                            @Field("date") String date,
                            @Field("weight") int weight,
                            @Field("height") int height,
                            @Field("code") int code);

    @FormUrlEncoded
    @POST("/growth/update")
    Call<Void> growth_update(@Field("baby_name") String baby_name,
                            @Field("date") String date,
                            @Field("weight") int weight,
                            @Field("height") int height,
                            @Field("code") int code);

    @FormUrlEncoded
    @POST("/growth/select")
    Call<JsonArray> growth_graph(@Field("baby_name") String baby_name,
                                 @Field("code") int code);

    @FormUrlEncoded
    @POST("/story/write")
    Call<Void> story_write(@Field("baby_name") String baby_name,
                           @Field("idx") int idx,
                           @Field("story") String story,
                           @Field("code") int code);
}
