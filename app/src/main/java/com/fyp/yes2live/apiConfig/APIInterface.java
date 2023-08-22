package com.fyp.yes2live.apiConfig;

import com.fyp.yes2live.model.DiabeticPatient;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.model.UserDto;
import com.fyp.yes2live.response.AddFoodResponse;
import com.fyp.yes2live.response.BaseResponse;
import com.fyp.yes2live.response.DeleteExerciseItem;
import com.fyp.yes2live.response.DeleteFoodItem;
import com.fyp.yes2live.response.ExerciseBaseResponse;
import com.fyp.yes2live.response.ExerciseDoneBaseResponse;
import com.fyp.yes2live.response.ExerciseItemResponse;
import com.fyp.yes2live.response.FastingSugarTestResponse;
import com.fyp.yes2live.response.FoodItemResponse;
import com.fyp.yes2live.response.GeneralResponse;
import com.fyp.yes2live.response.GetBurnedCaloriesResponse;
import com.fyp.yes2live.response.GetExerciseListResponse;
import com.fyp.yes2live.response.GetExerciseLogResponse;
import com.fyp.yes2live.response.GetHealthDetails;
import com.fyp.yes2live.response.GetLogDataResponse;
import com.fyp.yes2live.response.GetPerDayLogDataResponse;
import com.fyp.yes2live.response.LogListBaseResponse;
import com.fyp.yes2live.response.RandomSugarTestResponse;
import com.fyp.yes2live.response.SearchExerciseResponse;
import com.fyp.yes2live.response.SearchItemBaseResponse;
import com.fyp.yes2live.response.SearchItemResponse;
import com.fyp.yes2live.response.UpdateExerciseResponse;
import com.fyp.yes2live.response.UpdateFoodIntakeResponse;
import com.fyp.yes2live.response.UserAssessmentResponse;
import com.fyp.yes2live.response.UserBaseResponse;
import com.fyp.yes2live.response.UserListBaseResponse;

import java.sql.Date;
import java.time.LocalDate;
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
            @Body UserDto user
    );
    @POST("user/signUp") //APIs endpoint
    Call<BaseResponse> signUp(
            @Body UserDto user
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

    @GET("mealfood/getByItemName")
    Call<SearchItemBaseResponse> getItemName(
            @Query("itemName") String keyword
    );

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

    @GET("foodIntake/getItemList")
    Call<LogListBaseResponse> getItemList(
            @Query("user_id") long user_id,
            @Query("datee") Date datee,
            @Query("type") String type
    );

    //DeleteFoodItem...
    @DELETE("foodIntake/deleteMeal")
    Call<DeleteFoodItem> deleteMeal(
            @Query("user_id") long user_id,
            @Query("date") Date date,
            @Query("foodIntakeId") long intakeId
    );

    //getExerciseList
    @GET("exerciseTracking/getExerciseList")
    Call<ExerciseDoneBaseResponse> getExerciseList(
            @Query("user_id") long user_id,
            @Query("datee") Date date
    );

    //DeleteFoodItem...
    @DELETE("exerciseTracking/deleteExercise")
    Call<DeleteExerciseItem> deleteExDone(
            @Query("user_id") long user_id,
            @Query("date") Date date,
            @Query("exerciseTrackingId") int exerciseTrackingId
    );

    //GetAllExerciseList
    @GET("exercise/getList")// used on track page click on exercise
    Call<ExerciseBaseResponse> viewTrackingExercises(
            @Query("key") String keyword
    );

    //Save Food Items
    @FormUrlEncoded
    @POST("foodIntake/saveMeal")
    Call<AddFoodResponse> saveMeal(
            @Field("user_id") long user_id,
            @Field("date") Date date,
            @Field("foodMealId") long foodMealId,
            @Field("type") String type,
            @Field("quantity") double quantity
    );

    //UpdateFoodIntake
    @PUT("foodIntake/updateFoodIntake")
    Call<UpdateFoodIntakeResponse> updateFoodIntake(
            @Query ("user_id") long user_id,
            @Query("date") String date,
            @Query("fdIntk_id") long fdIntk_id,
            @Query("quantity") double quantity

    );

    //GetBurnedCalories
    @GET("exerciseTracking/burnedKcal")
    Call<GetBurnedCaloriesResponse> burnedKcal(
            @Query("user_id") long user_id,
            @Query("exerciseId") long exerciseId,
            @Query("time_in_mins") int time_in_mins
    );

    //Save Food Items
    @FormUrlEncoded
    @POST("exerciseTracking/saveExercise")
    Call<GetExerciseLogResponse> exerciseLog(
            @Field("user_id") long user_id,
            @Field("date") Date date,
            @Field("exerciseId") long exerciseId,
            @Field("time_in_mins") int time_in_mins
    );
    //UpdateFoodIntake
    @PUT("exerciseTracking/updateExercise")
    Call<UpdateExerciseResponse> updateExerciseDone(
            @Query ("user_id") long user_id,
            @Query("date") Date date,
            @Query("exerciseTracking_id") long exerciseTracking_id,
            @Query("time_in_mins") int time_in_mins

    );
    @PUT("user/married") //APIs endpoint
    Call<UserBaseResponse> married(
            @Body User user
    );
    @PUT("user/pregnant") //APIs endpoint
    Call<UserBaseResponse> pregnant(
            @Body User user
    );
    @PUT("user/diabetesInFamily") //APIs endpoint
    Call<UserBaseResponse> diabetesInFamily(
            @Body User user
    );
    @PUT("user/otherDiseases") //APIs endpoint
    Call<UserBaseResponse> otherDiseases(
            @Body User user
    );
    @PUT("user/hba1c") //APIs endpoint
    Call<UserBaseResponse> hba1c(
            @Body User user
    );


    @POST("user_assessment")
    Call<UserAssessmentResponse> user_assessment(
            @Body DiabeticPatient diabeticPatient
    );

    @GET("getHealthDetails")
    Call<GetHealthDetails> getHealthDetails(
            @Query("user_id") long user_id,
            @Query("hba1c_date") String hba1c_date

    );

    @FormUrlEncoded
    @POST("fasting_sugar_test_report")
    Call<FastingSugarTestResponse> fasting_sugar_test_report(
            @Field("user_id") long user_id,
            @Field("diabetic_patient_id") int diabetic_patient_id,
            @Field("fasting_sugar_date") LocalDate fasting_sugar_date,
            @Field("fasting_sugar_ranges") int fasting_sugar_ranges
    );

    @FormUrlEncoded
    @POST("random_sugar_test_report")
    Call<RandomSugarTestResponse> random_sugar_test_report(
            @Field("user_id") long user_id,
            @Field("diabetic_patient_id") int diabetic_patient_id,
            @Field("random_sugar_date") LocalDate random_sugar_date,
            @Field("random_sugar_ranges") int random_sugar_ranges
    );
    @GET("foodIntake/getPerDayDietSummary")
    Call<GetPerDayLogDataResponse> getPerDietSummary(
            @Query("user_id") long user_id,
            @Query("date") Date date
    );

    @GET("mealfood/getNameList")
    Call<FoodItemResponse> getNameList();

    @POST("mealfood/add")
    Call<GeneralResponse> addMealFood(
            @Body SearchItemResponse item
    );
    @POST("exercise/add")
    Call<GeneralResponse> addExercise(
            @Body SearchExerciseResponse exercise
    );

    @GET("exercise/getNameList")
    Call<ExerciseItemResponse> getExerciseNameList();

    @GET("exercise/getByExerciseName")
    Call<ExerciseBaseResponse> getByExerciseName(
            @Query("itemName") String keyword
    );
    @DELETE("exercise/delete/{id}")
    Call<GeneralResponse> deleteExercise(
            @Path("id") long id
    );

    @DELETE("mealfood/delete/{id}")
    Call<GeneralResponse> deleteMealFood(
            @Path("id") long id
    );

    @GET("user/getListByName")
    Call<ExerciseItemResponse> getUserList();

    @GET("user/getByUserName")
    Call<UserListBaseResponse> getByUserName(
            @Query("userName") String userName);
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
