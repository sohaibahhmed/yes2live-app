package com.fyp.yes2live.apiConfig;

import com.fyp.yes2live.response.DietPlanResponse;
import com.fyp.yes2live.response.ExercisePlanResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DietplanApiInterface {

    @GET("user_dietPlan")
    Call<DietPlanResponse> user_dietPlan(
            @Query("DiseaseCategory") String DiseaseCategory,//dibetic or predibetic
            @Query("BreakfastCalories") Integer BreakfastCalories,//app dyge
            @Query("LunchCalories") Integer LunchCalories,
            @Query("SnackCalories") Integer SnackCalories,
            @Query("DinnerCalories") Integer DinnerCalories,
            @Query("bmi") int bmi
            //@Part HashMap<String, Object> params
    );

    @GET("exercise_dietPlan")
    Call<ExercisePlanResponse> exercise_dietPlan(
            @Query("DiseaseCategory") String DiseaseCategory,
            @Query("UserAL") Integer UserAL,
            @Query("ExTime") Integer ExTime,
            @Query("UserAge") Integer UserAge,
            @Query("UserPreference") Integer UserPreference
    );
}
