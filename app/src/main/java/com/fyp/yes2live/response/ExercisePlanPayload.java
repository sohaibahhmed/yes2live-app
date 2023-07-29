package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExercisePlanPayload {

    @SerializedName("ExerciseFoodPackage")
    @Expose
    private String ExerciseFoodPackage;

    public ExercisePlanPayload(String exerciseFoodPackage) {
        ExerciseFoodPackage = exerciseFoodPackage;
    }

    public String getExerciseFoodPackage() {
        return ExerciseFoodPackage;
    }

    public void setExerciseFoodPackage(String exerciseFoodPackage) {
        ExerciseFoodPackage = exerciseFoodPackage;
    }
}
