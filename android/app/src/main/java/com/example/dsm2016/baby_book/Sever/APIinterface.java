package com.example.dsm2016.baby_book.Sever;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

import com.google.gson.JsonArray;

import java.util.Date;
import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

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
    @Multipart
    @POST("/diary/add")
    Call<ResponseBody> save(@Part("baby_name") RequestBody baby_name,
                            @Part("date_time") RequestBody date_time,
                            @Part("subject") RequestBody subject,
                            @Part("diary") RequestBody diary,
                            @Part("code")RequestBody code,
                            @PartMap() Map<String, RequestBody> userfile);


}
