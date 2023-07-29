package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchItemResponse {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("calories")
    @Expose
    private String calories;
    @SerializedName("protein")
    @Expose
    private String protein;
    @SerializedName("fat")
    @Expose
    private String fat;
    @SerializedName("carbs")
    @Expose
    private String carbs;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("serving_type")
    @Expose
    private String serving_type;



    public SearchItemResponse(int log_food_items_id, String items_name, String calories, String proteins, String fat, String carbs, String quantity, String serving_type) {
        this.id = log_food_items_id;
        this.itemName = items_name;
        this.calories = calories;
        this.protein = proteins;
        this.fat = fat;
        this.carbs = carbs;
        this.quantity = quantity;
        this.serving_type = serving_type;
    }

    public SearchItemResponse(int log_food_items_id) {
        this.id = log_food_items_id;
    }

    public int getLog_food_items_id() {
        return id;
    }

    public void setLog_food_items_id(int log_food_items_id) {
        this.id = log_food_items_id;
    }

    public String getItems_name() {
        return itemName;
    }

    public void setItems_name(String items_name) {
        this.itemName = items_name;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getServing_type() {
        return serving_type;
    }

    public void setServing_type(String serving_type) {
        this.serving_type = serving_type;
    }
}