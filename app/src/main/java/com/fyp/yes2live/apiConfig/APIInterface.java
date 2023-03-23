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
    @GET("login")
    Call<BaseResponse> login(
            @Query("email") String email,
            @Query("password") String password
    );
    @POST("signUp") //APIs endpoint
    Call<BaseResponse> signUp(
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
