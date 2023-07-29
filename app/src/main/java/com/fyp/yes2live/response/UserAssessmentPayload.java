package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAssessmentPayload {

    @SerializedName("diabetic_patient_id")
    @Expose
    public int diabetic_patient_id;
    @SerializedName("activity_level")
    @Expose
    public float activity_level;
    @SerializedName("bmi")
    @Expose
    public float bmi;
    @SerializedName("disease_total_calories")
    @Expose
    public int disease_total_calories;
    @SerializedName("carbs")
    @Expose
    public int carbs;
    @SerializedName("fat")
    @Expose
    public int fat;
    @SerializedName("proteins")
    @Expose
    public int proteins;
    @SerializedName("b_calories")
    @Expose
    public int b_calories;
    @SerializedName("l_calories")
    @Expose
    public int l_calories;
    @SerializedName("s_calories")
    @Expose
    public int s_calories;
    @SerializedName("d_calories")
    @Expose
    public int d_calories;
    @SerializedName("disease_category")
    @Expose
    public String disease_category;
    @SerializedName("time_in_mins")
    @Expose
    public int time_in_mins;
    @SerializedName("age")
    @Expose
    public int age;

    public UserAssessmentPayload(int diabetic_patient_id, float activity_level, float bmi, int disease_total_calories, int carbs, int fat, int proteins, int b_calories, int l_calories, int s_calories, int d_calories, String disease_category, int time_in_mins, int age) {
        this.diabetic_patient_id = diabetic_patient_id;
        this.activity_level = activity_level;
        this.bmi = bmi;
        this.disease_total_calories = disease_total_calories;
        this.carbs = carbs;
        this.fat = fat;
        this.proteins = proteins;
        this.b_calories = b_calories;
        this.l_calories = l_calories;
        this.s_calories = s_calories;
        this.d_calories = d_calories;
        this.disease_category = disease_category;
        this.time_in_mins = time_in_mins;
        this.age = age;
    }

    public UserAssessmentPayload(float activity_level, float bmi, int disease_total_calories, int carbs, int fat, int proteins, int b_calories, int l_calories, int s_calories, int d_calories, String disease_category) {
        this.activity_level = activity_level;
        this.bmi = bmi;
        this.disease_total_calories = disease_total_calories;
        this.carbs = carbs;
        this.fat = fat;
        this.proteins = proteins;
        this.b_calories = b_calories;
        this.l_calories = l_calories;
        this.s_calories = s_calories;
        this.d_calories = d_calories;
        this.disease_category = disease_category;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDiabetic_patient_id() {
        return diabetic_patient_id;
    }

    public void setDiabetic_patient_id(int diabetic_patient_id) {
        this.diabetic_patient_id = diabetic_patient_id;
    }

    public float getActivity_level() {
        return activity_level;
    }

    public void setActivity_level(float activity_level) {
        this.activity_level = activity_level;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public int getDisease_total_calories() {
        return disease_total_calories;
    }

    public void setDisease_total_calories(int disease_total_calories) {
        this.disease_total_calories = disease_total_calories;
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

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getB_calories() {
        return b_calories;
    }

    public void setB_calories(int b_calories) {
        this.b_calories = b_calories;
    }

    public int getL_calories() {
        return l_calories;
    }

    public void setL_calories(int l_calories) {
        this.l_calories = l_calories;
    }

    public int getS_calories() {
        return s_calories;
    }

    public void setS_calories(int s_calories) {
        this.s_calories = s_calories;
    }

    public int getD_calories() {
        return d_calories;
    }

    public void setD_calories(int d_calories) {
        this.d_calories = d_calories;
    }

    public String getDisease_category() {
        return disease_category;
    }

    public void setDisease_category(String disease_category) {
        this.disease_category = disease_category;
    }

    public int getTime_in_mins() {
        return time_in_mins;
    }

    public void setTime_in_mins(int time_in_mins) {
        this.time_in_mins = time_in_mins;
    }

    @Override
    public String toString() {
        return "UserAssessmentPayload{" +
                "diabetic_patient_id=" + diabetic_patient_id +
                ", activity_level=" + activity_level +
                ", bmi=" + bmi +
                ", disease_total_calories=" + disease_total_calories +
                ", carbs=" + carbs +
                ", fat=" + fat +
                ", proteins=" + proteins +
                ", b_calories=" + b_calories +
                ", l_calories=" + l_calories +
                ", s_calories=" + s_calories +
                ", d_calories=" + d_calories +
                ", disease_category='" + disease_category + '\'' +
                '}';
    }
}