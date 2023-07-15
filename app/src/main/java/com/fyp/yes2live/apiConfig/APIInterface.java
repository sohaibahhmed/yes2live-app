package com.fyp.yes2live.apiConfig;

import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.AddFoodResponse;
import com.fyp.yes2live.response.BaseResponse;
import com.fyp.yes2live.response.DeleteExerciseItem;
import com.fyp.yes2live.response.DeleteFoodItem;
import com.fyp.yes2live.response.ExerciseBaseResponse;
import com.fyp.yes2live.response.GetBurnedCaloriesResponse;
import com.fyp.yes2live.response.GetExerciseListResponse;
import com.fyp.yes2live.response.GetExerciseLogResponse;
import com.fyp.yes2live.response.GetLogDataResponse;
import com.fyp.yes2live.response.GetLogListResponse;
import com.fyp.yes2live.response.GetPerDayLogDataResponse;
import com.fyp.yes2live.response.SearchExerciseResponse;
import com.fyp.yes2live.response.SearchItemBaseResponse;
import com.fyp.yes2live.response.SearchItemResponse;
import com.fyp.yes2live.response.UpdateExerciseResponse;
import com.fyp.yes2live.response.UpdateFoodIntakeResponse;
import com.fyp.yes2live.response.UserBaseResponse;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    // in this class we define the endpoints of all our api's
    @POST("user/login")
    Call<BaseResponse> login(// login method
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

    @GET("user/getByID/{userID}") //APIs endpoint used in dietry info
    Call<UserBaseResponse> userProfile(
            @Path("userID") long userID
    );

    @POST("user/calculateCalories") //APIs endpoint//on next button of activity level
    Call<UserBaseResponse> calculateCalories(
            @Body User user
    );

    @GET("user/bmiRange/{userID}") //APIs endpoint tell ur weight range on target weight
    Call<UserBaseResponse> Bmi_range(
            @Path("userID") long userID
    );

    @POST("user/calculateCaloriesBasedOnWeightLoss") //APIs endpoint used on next button of target weight page
    Call<UserBaseResponse> calculateCaloriesBasedOnWeightLoss(
            @Body User user
    );

//    @GET("getList")
//    Call<List<SearchItemResponse>> getList(
//            @Query("key") String keyword
//    );

    @GET("mealfood/getList")//used in on track page (when click on add sign )
    Call<SearchItemBaseResponse> getList1(
            @Query("key") String keyword
    );

    @GET("getLogData")
    Call<GetLogDataResponse> getLogData(
            @Query("user_id") long user_id
    );

    @GET("getPerDayLogData")
    Call<GetPerDayLogDataResponse> getPerDayLogData(
            @Query("user_id") long user_id,
            @Query("date") String date
    );

    @GET("getLunchList")
    Call<List<GetLogListResponse>> getLunchList(
            @Query("user_id") long user_id,
            @Query("datee") String datee
    );

    //DeleteFoodItem...
    @DELETE("deleteMe")
    Call<DeleteFoodItem> deleteMe(
            @Query("user_id") long user_id,
            @Query("date") LocalDate date,
            @Query("intakeId") int intakeId
    );

    @GET("getBreakfastList")
    Call<List<GetLogListResponse>> getBreakfastList(
            @Query("user_id") long user_id,
            @Query("datee") String datee
    );

    //GetMorningSnacksList
    @GET("getMorningSnacksList")
    Call<List<GetLogListResponse>> getMorningSnacksList(
            @Query("user_id") long user_id,
            @Query("datee") String datee
    );

    //GetEveningSnacksList
    @GET("getEveningSnacksList")
    Call<List<GetLogListResponse>> getEveningSnacksList(
            @Query("user_id") long user_id,
            @Query("datee") String datee
    );

    //GetDinnerList
    @GET("getDinnerList")
    Call<List<GetLogListResponse>> getDinnerList(
            @Query("user_id") long user_id,
            @Query("datee") String datee
    );

    //getExerciseList
    @GET("getExerciseList")
    Call<List<GetExerciseListResponse>> getExerciseList(
            @Query("user_id") long user_id,
            @Query("date") String date
    );

    //DeleteFoodItem...
    @DELETE("deleteExDone")
    Call<DeleteExerciseItem> deleteExDone(
            @Query("user_id") long user_id,
            @Query("date") LocalDate date,
            @Query("exercise_done_id") int exercise_done_id
    );

    //GetAllExerciseList
    @GET("exercise/getList")// used on track page click on exercise
    Call<ExerciseBaseResponse> viewTrackingExercises(
            @Query("key") String keyword
    );

    //Save Food Items
    @FormUrlEncoded
    @POST("saveMe")
    Call<AddFoodResponse> saveMe(
            @Field("user_id") long user_id,
            @Field("date") String date,
            @Field("log_food_items_id") int log_food_items_id,
            @Field("type") String type,
            @Field("calories") double calories,
            @Field("carbs") double carbs,
            @Field("proteins") double proteins,
            @Field("fat") double fat,
            @Field("quantity") double quantity
    );

    //UpdateFoodIntake
    @PUT("updateFoodIntake")
    Call<UpdateFoodIntakeResponse> updateFoodIntake(
            @Query ("user_id") long user_id,
            @Query("date") String date,
            @Query("fdIntk_id") int fdIntk_id,
            @Query("quantity") double quantity

    );

    //GetBurnedCalories
    @GET("burnedKcal")
    Call<GetBurnedCaloriesResponse> burnedKcal(
            @Query("user_id") long user_id,
            @Query("exerciseTracking_id") int exerciseTracking_id,
            @Query("time_in_mins") int time_in_mins
    );

    //Save Food Items
    @FormUrlEncoded
    @POST("exerciseLog")
    Call<GetExerciseLogResponse> exerciseLog(
            @Field("user_id") long user_id,
            @Field("date") String date,
            @Field("exercise_tracking_id") int exercise_tracking_id,
            @Field("calories_burned") double calories_burned,
            @Field("time_in_mins") int time_in_mins
    );
    //UpdateFoodIntake
    @PUT("updateExerciseDone")
    Call<UpdateExerciseResponse> updateExerciseDone(
            @Query ("user_id") long user_id,
            @Query("date") String date,
            @Query("ExDone_id") int ExDone_id,
            @Query("time_in_mins") int time_in_mins

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
