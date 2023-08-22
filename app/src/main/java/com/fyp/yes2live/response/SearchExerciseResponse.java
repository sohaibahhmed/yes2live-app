package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchExerciseResponse {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("exceriseName")
    @Expose
    private String exerciseName;
    @SerializedName("met")
    @Expose
    private Float metValue;

    public SearchExerciseResponse(long exerciseTrackingId, String exerciseName, Float metValue) {
        this.id = exerciseTrackingId;
        this.exerciseName = exerciseName;
        this.metValue = metValue;
    }

    public SearchExerciseResponse(String exerciseName, Float metValue) {
        this.exerciseName = exerciseName;
        this.metValue = metValue;
    }

    public long getExerciseTrackingId() {
        return id;
    }

    public void setExerciseTrackingId(long exerciseTrackingId) {
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
