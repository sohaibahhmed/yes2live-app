package com.fyp.yes2live.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("phonenum")
    public int phonenumber;

    @SerializedName("id")
    public long id;

    @SerializedName("dob")
    private String dob;

    @SerializedName("gender")
    private String gender;

    @SerializedName("weight")
    private double weight;

    @SerializedName("height")
    private double height;

    @SerializedName("age")
    private double age;

    @SerializedName("activityLevel")
    private double activityLevel;
    @SerializedName("targetWeight")
    private double targetWeight;
    @SerializedName("peerWeekGoal")
    private double peerWeekGoal;
    private double weightLossCalories;
    @SerializedName("B_calories")
    private double B_calories;
    @SerializedName("ms_calories")
    private double ms_calories;
    @SerializedName("l_calories")
    private double l_calories;
    @SerializedName("es_calories")
    private double es_calories;
    @SerializedName("d_calories")
    private double d_calories;
    @SerializedName("t_proteins")
    private double t_proteins;
    @SerializedName("t_fat")
    private double t_fat;
    @SerializedName("t_carbs")
    private double t_carbs;


    public String getName() {
        return name;
    }

    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(long id, int age, String gender, double weight, double height) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(long id, double activityLevel) {
        this.id = id;
        this.activityLevel = activityLevel;
    }

    public User(long id, String name, String email, double weight, double height) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public User(long id, double targetWeight, double peerWeekGoal) {
        this.id = id;
        this.targetWeight = targetWeight;
        this.peerWeekGoal = peerWeekGoal;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(double activityLevel) {
        this.activityLevel = activityLevel;
    }

    public double getPeerWeekGoal() {
        return peerWeekGoal;
    }

    public void setPeerWeekGoal(double peerWeekGoal) {
        this.peerWeekGoal = peerWeekGoal;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public double getWeightLossCalories() {
        return weightLossCalories;
    }

    public void setWeightLossCalories(double weightLossCalories) {
        this.weightLossCalories = weightLossCalories;
    }

    public double getB_calories() {
        return B_calories;
    }

    public void setB_calories(double b_calories) {
        B_calories = b_calories;
    }

    public double getMs_calories() {
        return ms_calories;
    }

    public void setMs_calories(double ms_calories) {
        this.ms_calories = ms_calories;
    }

    public double getL_calories() {
        return l_calories;
    }

    public void setL_calories(double l_calories) {
        this.l_calories = l_calories;
    }

    public double getEs_calories() {
        return es_calories;
    }

    public void setEs_calories(double es_calories) {
        this.es_calories = es_calories;
    }

    public double getD_calories() {
        return d_calories;
    }

    public void setD_calories(double d_calories) {
        this.d_calories = d_calories;
    }

    public double getT_proteins() {
        return t_proteins;
    }

    public void setT_proteins(double t_proteins) {
        this.t_proteins = t_proteins;
    }

    public double getT_fat() {
        return t_fat;
    }

    public void setT_fat(double t_fat) {
        this.t_fat = t_fat;
    }

    public double getT_carbs() {
        return t_carbs;
    }

    public void setT_carbs(double t_carbs) {
        this.t_carbs = t_carbs;
    }
}
