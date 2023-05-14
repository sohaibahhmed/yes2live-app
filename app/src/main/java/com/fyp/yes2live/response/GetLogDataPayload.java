package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLogDataPayload {

    @SerializedName("t_bCalories")
    @Expose
    public int t_bCalories;
    @SerializedName("t_msCalories")
    @Expose
    public int t_msCalories;
    @SerializedName("t_lCalories")
    @Expose
    public int t_lCalories;
    @SerializedName("t_esCalories")
    @Expose
    public int t_esCalories;
    @SerializedName("t_dCalories")
    @Expose
    public int t_dCalories;
    @SerializedName("current_weight")
    @Expose
    public int current_weight;
    @SerializedName("target_weight")
    @Expose
    public int target_weight;
    @SerializedName("weightloss_calories")
    @Expose
    public int weightloss_calories;
    @SerializedName("reach_weeks")
    @Expose
    public String reach_weeks;
    @SerializedName("t_proteins")
    @Expose
    public int t_proteins;
    @SerializedName("t_fat")
    @Expose
    public int t_fat;
    @SerializedName("t_carbs")
    @Expose
    public int t_carbs;

    public GetLogDataPayload(int t_bCalories, int t_msCalories, int t_lCalories, int t_esCalories, int t_dCalories, int current_weight, int target_weight, int weightloss_calories, String reach_weeks, int t_proteins, int t_fat, int t_carbs) {
        this.t_bCalories = t_bCalories;
        this.t_msCalories = t_msCalories;
        this.t_lCalories = t_lCalories;
        this.t_esCalories = t_esCalories;
        this.t_dCalories = t_dCalories;
        this.current_weight = current_weight;
        this.target_weight = target_weight;
        this.weightloss_calories = weightloss_calories;
        this.reach_weeks = reach_weeks;
        this.t_proteins = t_proteins;
        this.t_fat = t_fat;
        this.t_carbs = t_carbs;
    }

    public int getT_bCalories() {
        return t_bCalories;
    }

    public void setT_bCalories(int t_bCalories) {
        this.t_bCalories = t_bCalories;
    }

    public int getT_msCalories() {
        return t_msCalories;
    }

    public void setT_msCalories(int t_msCalories) {
        this.t_msCalories = t_msCalories;
    }

    public int getT_lCalories() {
        return t_lCalories;
    }

    public void setT_lCalories(int t_lCalories) {
        this.t_lCalories = t_lCalories;
    }

    public int getT_esCalories() {
        return t_esCalories;
    }

    public void setT_esCalories(int t_esCalories) {
        this.t_esCalories = t_esCalories;
    }

    public int getT_dCalories() {
        return t_dCalories;
    }

    public void setT_dCalories(int t_dCalories) {
        this.t_dCalories = t_dCalories;
    }

    public int getCurrent_weight() {
        return current_weight;
    }

    public void setCurrent_weight(int current_weight) {
        this.current_weight = current_weight;
    }

    public int getTarget_weight() {
        return target_weight;
    }

    public void setTarget_weight(int target_weight) {
        this.target_weight = target_weight;
    }

    public int getWeightloss_calories() {
        return weightloss_calories;
    }

    public void setWeightloss_calories(int weightloss_calories) {
        this.weightloss_calories = weightloss_calories;
    }

    public String getReach_weeks() {
        return reach_weeks;
    }

    public void setReach_weeks(String reach_weeks) {
        this.reach_weeks = reach_weeks;
    }

    public int getT_proteins() {
        return t_proteins;
    }

    public void setT_proteins(int t_proteins) {
        this.t_proteins = t_proteins;
    }

    public int getT_fat() {
        return t_fat;
    }

    public void setT_fat(int t_fat) {
        this.t_fat = t_fat;
    }

    public int getT_carbs() {
        return t_carbs;
    }

    public void setT_carbs(int t_carbs) {
        this.t_carbs = t_carbs;
    }
}
