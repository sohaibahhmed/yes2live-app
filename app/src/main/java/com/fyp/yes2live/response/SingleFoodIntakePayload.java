package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleFoodIntakePayload {

    @SerializedName("itemsName")
    @Expose
    private String itemsName;
    @SerializedName("calories")
    @Expose
    private double calories;
    @SerializedName("carbs")
    @Expose
    private double carbs;
    @SerializedName("proteins")
    @Expose
    private double proteins;
    @SerializedName("fat")
    @Expose
    private double fat;
    @SerializedName("quantity")
    @Expose
    private double quantity;

    public SingleFoodIntakePayload(String itemsName, double calories, double carbs, double proteins, double fat, double quantity) {
        this.itemsName = itemsName;
        this.calories = calories;
        this.carbs = carbs;
        this.proteins = proteins;
        this.fat = fat;
        this.quantity = quantity;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}