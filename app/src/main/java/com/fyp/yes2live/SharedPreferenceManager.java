package com.fyp.yes2live;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.DietPlanPayload;
import com.fyp.yes2live.response.ExercisePlanPayload;
import com.fyp.yes2live.response.UserAssessmentPayload;

import java.sql.Date;
import java.time.LocalDate;

public class SharedPreferenceManager {
    private static String SHARED_PREF_NAME="usersession";
    private SharedPreferences sharedPreference;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPreferenceManager(Context context) {
        this.context=context;
    }

    public void saveUser(User user){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putLong("id",user.getId());
        editor.putString("name",user.getName());
        editor.putString("username",user.getUsername());
        editor.putString("email",user.getEmail());
        editor.putString("startGoalDate", user.getStartGoalDate());
        //for loggedin status... if loggedIn return true
        editor.putBoolean("logged",true);
        if(user.getTargetWeight() > 0){
            editor.putBoolean("saveTargetAndRange", true);
        }
        if(user.getActivityLevel() > 0){
            editor.putBoolean("activity_level2",true);
        }
        if(user.getAge() > 1){
            editor.putBoolean("saveAssessment",true);
        }
        editor.apply();
    }

    public User getUser(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(
                sharedPreference.getLong("id",-1),
                sharedPreference.getString("name",null),
                sharedPreference.getString("email",null),
                sharedPreference.getFloat("weight",0),
                sharedPreference.getFloat("height",0),
                sharedPreference.getString("username",null)

        );
    }

    public String getDate(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        String dateHistory=sharedPreference.getString("date",null);
        return dateHistory;
    }

    public Throwable saveDate(String date){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putString("date",date);
        editor.apply();
        return null;
    }

    public void saveActivityLevel(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putBoolean("activity_level2",true);
        editor.apply();

    }

    public void saveQuestion(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putBoolean("saveAssessment",true);
        editor.apply();
    }
    //Check whether user loggedIn or not
    public  boolean isLoggedIn(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("logged",false);

    }

    //Check whether user enter activity level
    public  boolean isActivityLevel(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("activity_level2",false);

    }
    public  boolean isQuestionnaire(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("saveAssessment",false);

    }

    public void saveRanges() {
        sharedPreference = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
        editor.putInt("TargetWeight",1);
        editor.putBoolean("saveTargetAndRange", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            editor.putString("startGoalDate",LocalDate.now().toString());
        }
        editor.apply();
    }

    //Check whether user enter Targetweight and ranges
    public  boolean isSavedTargetWeight(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("saveTargetAndRange",false);

    }

    public void logout(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.clear();
        editor.apply();

    }

    public void saveReportDate(String date){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putString("Date",date);
        editor.putBoolean("saveDate",true);
        editor.apply();
    }

    public void saveUserDiabeticAssessment(UserAssessmentPayload userAssessmentPayload){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        if(userAssessmentPayload != null){
            editor.putInt("diabetic_patient_id",userAssessmentPayload.getDiabetic_patient_id());
            editor.putFloat("activity_level",userAssessmentPayload.getActivity_level());
            editor.putFloat("bmi",userAssessmentPayload.getBmi());
            editor.putInt("disease_total_calories",userAssessmentPayload.getDisease_total_calories());
            editor.putInt("carbs",userAssessmentPayload.getCarbs());
            editor.putInt("fat",userAssessmentPayload.getFat());
            editor.putInt("proteins",userAssessmentPayload.getProteins());
            editor.putInt("b_calories", userAssessmentPayload.getB_calories());
            editor.putInt("l_calories",userAssessmentPayload.getL_calories());
            editor.putInt("s_calories",userAssessmentPayload.getS_calories());
            editor.putInt("d_calories",userAssessmentPayload.getD_calories());
            editor.putString("disease_category",userAssessmentPayload.getDisease_category());
            editor.putInt("time_in_mins",userAssessmentPayload.getTime_in_mins());
            editor.putInt("age",userAssessmentPayload.getAge());
            editor.putBoolean("saved",true);
            editor.apply();
        }

    }

    public void saveUserWarning(String i){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putString("message",i);
        editor.putBoolean("warning",true);
        editor.apply();
    }
    public String getUserWarning(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        String warning=sharedPreference.getString("message",null);
        return warning;
    }

    public String getReportDate(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        String date=sharedPreference.getString("Date",null);
        return date;
    }

    public void saveUserPreference(int i){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putInt("preference",1);
        editor.putBoolean("savePreference",true);
        editor.apply();
    }

    public  boolean isUserWarning(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("warning",false);
    }

    public UserAssessmentPayload getUserDiabeticAssessment(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new UserAssessmentPayload(
                sharedPreference.getInt("diabetic_patient_id",1),
                sharedPreference.getFloat("activity_level",1),
                sharedPreference.getFloat("bmi",1),
                sharedPreference.getInt("disease_total_calories",1),
                sharedPreference.getInt("carbs",1),
                sharedPreference.getInt("fat",1),
                sharedPreference.getInt("proteins",1),
                sharedPreference.getInt("b_calories",1),
                sharedPreference.getInt("l_calories",1),
                sharedPreference.getInt("s_calories",1),
                sharedPreference.getInt("d_calories",1),
                sharedPreference.getString("disease_category",null),
                sharedPreference.getInt("time_in_mins",1),
                sharedPreference.getInt("age",1)
        );
    }

    public DietPlanPayload getPlan1(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new DietPlanPayload(
                sharedPreference.getString("Breakfast",null),
                sharedPreference.getString("Lunch",null),
                sharedPreference.getString("Snack",null),
                sharedPreference.getString("Dinner",null)
        );
    }

    //Check whether user loggedIn or not
    public  boolean isPlan1(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("plan1",false);

    }

    public void saveplan1(DietPlanPayload i){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putString("Breakfast",i.getBreakfastFoodPackage());
        editor.putString("Lunch",i.getLunchFoodPackage());
        editor.putString("Snack",i.getSnackFoodPackage());
        editor.putString("Dinner",i.getDinnerFoodPackage());
        editor.putBoolean("plan1",true);
        editor.apply();
    }

    public  boolean isPlan2(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("plan2",false);

    }

    public DietPlanPayload getPlan2(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new DietPlanPayload(
                sharedPreference.getString("Breakfast2",null),
                sharedPreference.getString("Lunch2",null),
                sharedPreference.getString("Snack2",null),
                sharedPreference.getString("Dinner2",null)
        );
    }

    public void saveplan2(DietPlanPayload i){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putString("Breakfast2",i.getBreakfastFoodPackage());
        editor.putString("Lunch2",i.getLunchFoodPackage());
        editor.putString("Snack2",i.getSnackFoodPackage());
        editor.putString("Dinner2",i.getDinnerFoodPackage());
        editor.putBoolean("plan2",true);
        editor.apply();
    }

    public void saveplan3(DietPlanPayload i){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putString("Breakfast3",i.getBreakfastFoodPackage());
        editor.putString("Lunch3",i.getLunchFoodPackage());
        editor.putString("Snack3",i.getSnackFoodPackage());
        editor.putString("Dinner3",i.getDinnerFoodPackage());
        editor.putBoolean("plan3",true);
        editor.apply();
    }

    public  boolean isPlan3(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("plan3",false);

    }

    public DietPlanPayload getPlan3(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new DietPlanPayload(
                sharedPreference.getString("Breakfast3",null),
                sharedPreference.getString("Lunch3",null),
                sharedPreference.getString("Snack3",null),
                sharedPreference.getString("Dinner3",null)
        );
    }

    public DietPlanPayload getPlan4(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new DietPlanPayload(
                sharedPreference.getString("Breakfast4",null),
                sharedPreference.getString("Lunch4",null),
                sharedPreference.getString("Snack4",null),
                sharedPreference.getString("Dinner4",null)
        );
    }

    public  boolean isPlan4(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("plan4",false);

    }

    public void saveplan4(DietPlanPayload i){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putString("Breakfast4",i.getBreakfastFoodPackage());
        editor.putString("Lunch4",i.getLunchFoodPackage());
        editor.putString("Snack4",i.getSnackFoodPackage());
        editor.putString("Dinner4",i.getDinnerFoodPackage());
        editor.putBoolean("plan4",true);
        editor.apply();
    }

    public  boolean isSelectPrefernce(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("savePreference",false);
    }

    public  boolean isExercisePlan1(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("ex_plan1",false);

    }

    public  boolean isExercisePlan2(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("ex_plan2",false);
    }

    public ExercisePlanPayload getExPlan1(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new ExercisePlanPayload(
                sharedPreference.getString("Exercise",null)
        );
    }

    public ExercisePlanPayload getExPlan2(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new ExercisePlanPayload(
                sharedPreference.getString("Exercise",null)
        );
    }

    public void saveExerciseplan1(ExercisePlanPayload i){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putString("Exercise",i.getExerciseFoodPackage());
        editor.putBoolean("ex_plan1",true);
        editor.apply();
    }

    public void saveExerciseplan2(ExercisePlanPayload i){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putString("Exercise",i.getExerciseFoodPackage());
        editor.putBoolean("ex_plan2",true);
        editor.apply();
    }

    public int getUserPreference(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        int prefer=sharedPreference.getInt("preference",1);
        return prefer;
    }

    public  String getGoalStartDate(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getString("startGoalDate",null);

    }

    public  boolean isDataSaved(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //if user loggedIn return true otherwise return false
        return sharedPreference.getBoolean("saveDate",false);

    }
}
