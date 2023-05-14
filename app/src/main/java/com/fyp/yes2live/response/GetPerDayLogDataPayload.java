package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPerDayLogDataPayload {

    @SerializedName("totalEatenCalories")
    @Expose
    public int totalEatenCalories;
    @SerializedName("totalEatenCarbs")
    @Expose
    public int totalEatenCarbs;
    @SerializedName("totalEatenFat")
    @Expose
    public int totalEatenFat;
    @SerializedName("totalEatenProtein")
    @Expose
    public int totalEatenProtein;
    @SerializedName("totalEaten_BCalories")
    @Expose
    public int totalEaten_BCalories;
    @SerializedName("totalEaten_DCalories")
    @Expose
    public int totalEaten_DCalories;
    @SerializedName("totalEaten_ESCalories")
    @Expose
    public int totalEaten_ESCalories;
    @SerializedName("totalEaten_MSCalories")
    @Expose
    public int totalEaten_MSCalories;
    @SerializedName("totalEaten_LCalories")
    @Expose
    public int totalEaten_LCalories;
    @SerializedName("totalBurnedCalories")
    @Expose
    public int totalBurnedCalories;

    public GetPerDayLogDataPayload(int totalEatenCalories, int totalEatenCarbs, int totalEatenFat, int totalEatenProtein, int totalEaten_BCalories, int totalEaten_DCalories, int totalEaten_ESCalories, int totalEaten_MSCalories, int totalEaten_LCalories, int totalBurnedCalories) {
        this.totalEatenCalories = totalEatenCalories;
        this.totalEatenCarbs = totalEatenCarbs;
        this.totalEatenFat = totalEatenFat;
        this.totalEatenProtein = totalEatenProtein;
        this.totalEaten_BCalories = totalEaten_BCalories;
        this.totalEaten_DCalories = totalEaten_DCalories;
        this.totalEaten_ESCalories = totalEaten_ESCalories;
        this.totalEaten_MSCalories = totalEaten_MSCalories;
        this.totalEaten_LCalories = totalEaten_LCalories;
        this.totalBurnedCalories = totalBurnedCalories;
    }

    public int getTotalEatenCalories() {
        return totalEatenCalories;
    }

    public void setTotalEatenCalories(int totalEatenCalories) {
        this.totalEatenCalories = totalEatenCalories;
    }

    public int getTotalEatenCarbs() {
        return totalEatenCarbs;
    }

    public void setTotalEatenCarbs(int totalEatenCarbs) {
        this.totalEatenCarbs = totalEatenCarbs;
    }

    public int getTotalEatenFat() {
        return totalEatenFat;
    }

    public void setTotalEatenFat(int totalEatenFat) {
        this.totalEatenFat = totalEatenFat;
    }

    public int getTotalEatenProtein() {
        return totalEatenProtein;
    }

    public void setTotalEatenProtein(int totalEatenProtein) {
        this.totalEatenProtein = totalEatenProtein;
    }

    public int getTotalEaten_BCalories() {
        return totalEaten_BCalories;
    }

    public void setTotalEaten_BCalories(int totalEaten_BCalories) {
        this.totalEaten_BCalories = totalEaten_BCalories;
    }

    public int getTotalEaten_DCalories() {
        return totalEaten_DCalories;
    }

    public void setTotalEaten_DCalories(int totalEaten_DCalories) {
        this.totalEaten_DCalories = totalEaten_DCalories;
    }

    public int getTotalEaten_ESCalories() {
        return totalEaten_ESCalories;
    }

    public void setTotalEaten_ESCalories(int totalEaten_ESCalories) {
        this.totalEaten_ESCalories = totalEaten_ESCalories;
    }

    public int getTotalEaten_MSCalories() {
        return totalEaten_MSCalories;
    }

    public void setTotalEaten_MSCalories(int totalEaten_MSCalories) {
        this.totalEaten_MSCalories = totalEaten_MSCalories;
    }

    public int getTotalEaten_LCalories() {
        return totalEaten_LCalories;
    }

    public void setTotalEaten_LCalories(int totalEaten_LCalories) {
        this.totalEaten_LCalories = totalEaten_LCalories;
    }

    public int getTotalBurnedCalories() {
        return totalBurnedCalories;
    }

    public void setTotalBurnedCalories(int totalBurnedCalories) {
        this.totalBurnedCalories = totalBurnedCalories;
    }
}
