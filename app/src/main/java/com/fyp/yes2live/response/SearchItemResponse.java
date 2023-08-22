package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchItemResponse {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("calories")
    @Expose
    private double calories;
    @SerializedName("protein")
    @Expose
    private double protein;
    @SerializedName("fat")
    @Expose
    private double fat;
    @SerializedName("carbs")
    @Expose
    private double carbs;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("servingType")
    @Expose
    private String serving_type;



    public SearchItemResponse(long log_food_items_id, String items_name, double calories, double proteins, double fat, double carbs, int quantity, String serving_type) {
        this.id = log_food_items_id;
        this.itemName = items_name;
        this.calories = calories;
        this.protein = proteins;
        this.fat = fat;
        this.carbs = carbs;
        this.quantity = quantity;
        this.serving_type = serving_type;
    }

    public SearchItemResponse(String itemName, double calories, double protein, double fat, double carbs, int quantity, String serving_type) {
        this.itemName = itemName;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.quantity = quantity;
        this.serving_type = serving_type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public SearchItemResponse(long log_food_items_id) {
        this.id = log_food_items_id;
    }

    public long getLog_food_items_id() {
        return id;
    }

    public void setLog_food_items_id(long log_food_items_id) {
        this.id = log_food_items_id;
    }

    public String getItems_name() {
        return itemName;
    }

    public void setItems_name(String items_name) {
        this.itemName = items_name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getServing_type() {
        return serving_type;
    }

    public void setServing_type(String serving_type) {
        this.serving_type = serving_type;
    }
}