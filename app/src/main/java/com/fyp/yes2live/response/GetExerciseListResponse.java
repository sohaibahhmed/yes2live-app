package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetExerciseListResponse {

    @SerializedName("exercise_done_id")
    @Expose
    public int exercise_done_id;
    @SerializedName("calories_burned")
    @Expose
    public String calories_burned;
    @SerializedName("time_in_mins")
    @Expose
    public String time_in_mins;
    @SerializedName("exercise_name")
    @Expose
    public String exercise_name;

    public GetExerciseListResponse(int exercise_done_id, String calories_burned, String time_in_mins, String exercise_name) {
        this.exercise_done_id = exercise_done_id;
        this.calories_burned = calories_burned;
        this.time_in_mins = time_in_mins;
        this.exercise_name = exercise_name;
    }

    public int getExercise_done_id() {
        return exercise_done_id;
    }

    public void setExercise_done_id(int exercise_done_id) {
        this.exercise_done_id = exercise_done_id;
    }

    public String getCalories_burned() {
        return calories_burned;
    }

    public void setCalories_burned(String calories_burned) {
        this.calories_burned = calories_burned;
    }

    public String getTime_in_mins() {
        return time_in_mins;
    }

    public void setTime_in_mins(String time_in_mins) {
        this.time_in_mins = time_in_mins;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }
}
