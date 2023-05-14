package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchExerciseResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("exceriseName")
    @Expose
    private String exerciseName;
    @SerializedName("met_value")
    @Expose
    private Float metValue;

    public SearchExerciseResponse(Integer exerciseTrackingId, String exerciseName, Float metValue) {
        this.id = exerciseTrackingId;
        this.exerciseName = exerciseName;
        this.metValue = metValue;
    }

    public Integer getExerciseTrackingId() {
        return id;
    }

    public void setExerciseTrackingId(Integer exerciseTrackingId) {
        this.id = exerciseTrackingId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public Float getMetValue() {
        return metValue;
    }

    public void setMetValue(Float metValue) {
        this.metValue = metValue;
    }
}
