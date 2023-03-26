package com.fyp.yes2live.apiConfig;

import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.BaseResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    @POST("user/login")
    Call<BaseResponse> login(
            @Body User user
    );
    @POST("user/signUp") //APIs endpoint
    Call<BaseResponse> signUp(
            @Body User user
    );

    @POST("user/update") //APIs endpoint
    Call<BaseResponse> update(
            @Body User user
    );

//    @POST("/api/users")
//    Call<User> createUser(@Body User user);
//
//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);
//
//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
