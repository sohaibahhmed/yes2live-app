package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DietPlanPayload {

    @SerializedName("BreakfastFoodPackage")
    @Expose
    private String BreakfastFoodPackage;
    @SerializedName("DinnerFoodPackage")
    @Expose
    private String DinnerFoodPackage;
    @SerializedName("LunchFoodPackage")
    @Expose
    private String LunchFoodPackage;
    @SerializedName("SnackFoodPackage")
    @Expose
    private String SnackFoodPackage;
    @SerializedName("koi_variable")
    @Expose
    private String koi_variable;

    public DietPlanPayload(String breakfastFoodPackage, String dinnerFoodPackage, String lunchFoodPackage, String snackFoodPackage) {
        BreakfastFoodPackage = breakfastFoodPackage;
        DinnerFoodPackage = dinnerFoodPackage;
        LunchFoodPackage = lunchFoodPackage;
        SnackFoodPackage = snackFoodPackage;
    }

    public DietPlanPayload(String breakfastFoodPackage, String dinnerFoodPackage, String lunchFoodPackage, String snackFoodPackage, String koi_variable) {
        BreakfastFoodPackage = breakfastFoodPackage;
        DinnerFoodPackage = dinnerFoodPackage;
        LunchFoodPackage = lunchFoodPackage;
        SnackFoodPackage = snackFoodPackage;
        this.koi_variable = koi_variable;
    }

    public String getBreakfastFoodPackage() {
        return BreakfastFoodPackage;
    }

    public void setBreakfastFoodPackage(String breakfastFoodPackage) {
        BreakfastFoodPackage = breakfastFoodPackage;
    }

    public String getDinnerFoodPackage() {
        return DinnerFoodPackage;
    }

    public void setDinnerFoodPackage(String dinnerFoodPackage) {
        DinnerFoodPackage = dinnerFoodPackage;
    }

    public String getLunchFoodPackage() {
        return LunchFoodPackage;
    }

    public void setLunchFoodPackage(String lunchFoodPackage) {
        LunchFoodPackage = lunchFoodPackage;
    }

    public String getSnackFoodPackage() {
        return SnackFoodPackage;
    }

    public void setSnackFoodPackage(String snackFoodPackage) {
        SnackFoodPackage = snackFoodPackage;
    }

    public String getKoi_variable() {
        return koi_variable;
    }

    public void setKoi_variable(String koi_variable) {
        this.koi_variable = koi_variable;
    }
}
