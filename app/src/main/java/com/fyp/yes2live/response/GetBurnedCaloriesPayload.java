package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBurnedCaloriesPayload {

    @SerializedName("exercise_id")
    @Expose
    public int exercise_id;
    @SerializedName("exercise_name")
    @Expose
    public String exercise_name;
    @SerializedName("calories")
    @Expose
    public String calories;
    @SerializedName("time_in_mins")
    @Expose
    public int time_in_mins;

    public GetBurnedCaloriesPayload(int exercise_id, String exercise_name, String calories, int time_in_mins) {
        this.exercise_id = exercise_id;
        this.exercise_name = exercise_name;
        this.calories = calories;
        this.time_in_mins = time_in_mins;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public int getTime_in_mins() {
        return time_in_mins;
    }

    public void setTime_in_mins(int time_in_mins) {
        this.time_in_mins = time_in_mins;
    }
}
