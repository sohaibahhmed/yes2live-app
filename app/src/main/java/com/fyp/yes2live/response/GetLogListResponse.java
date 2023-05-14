package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLogListResponse {

    @SerializedName("intakeId")
    @Expose
    public int intakeId;
    @SerializedName("calories")
    @Expose
    public Double calories;
    @SerializedName("quantity")
    @Expose
    public String quantity;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("servingType")
    @Expose
    public String servingType;
    @SerializedName("proteins")
    @Expose
    public Double proteins;
    @SerializedName("carbs")
    @Expose
    public Double carbs;
    @SerializedName("fat")
    @Expose
    public Double fat;

    public GetLogListResponse(int intakeId, Double calories, String quantity, String itemName, String servingType, Double proteins, Double carbs, Double fat) {
        this.intakeId = intakeId;
        this.calories = calories;
        this.quantity = quantity;
        this.itemName = itemName;
        this.servingType = servingType;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fat = fat;
    }

    public int getIntakeId() {
        return intakeId;
    }

    public void setIntakeId(int intakeId) {
        this.intakeId = intakeId;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
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

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }
}
