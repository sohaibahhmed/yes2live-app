package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLogListResponse {

    @SerializedName("intakeId")
    @Expose
    public long intakeId;
    @SerializedName("calories")
    @Expose
    public int calories;
    @SerializedName("quantity")
    @Expose
    public double quantity;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("servingType")
    @Expose
    public String servingType;
    @SerializedName("proteins")
    @Expose
    public int proteins;
    @SerializedName("carbs")
    @Expose
    public int carbs;
    @SerializedName("fat")
    @Expose
    public int fat;

    public GetLogListResponse(int intakeId, int calories, double quantity, String itemName, String servingType, int proteins, int carbs, int fat) {
        this.intakeId = intakeId;
        this.calories = calories;
        this.quantity = quantity;
        this.itemName = itemName;
        this.servingType = servingType;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fat = fat;
    }

    public long getIntakeId() {
        return intakeId;
    }

    public void setIntakeId(long intakeId) {
        this.intakeId = intakeId;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getServingType() {
        return servingType;
    }

    public void setServingType(String servingType) {
        this.servingType = servingType;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }
}
