package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHealthDetailsPayload {

    @SerializedName("diabetic_patient_id")
    @Expose
    private int diabetic_patient_id;
    @SerializedName("age")
    @Expose
    private int age;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("height")
    @Expose
    private double height;
    @SerializedName("weight")
    @Expose
    private double weight;
    @SerializedName("improvement_status")
    @Expose
    private String improvement_status;
    @SerializedName("disease_category")
    @Expose
    private String disease_category;
    @SerializedName("hba1c_date")
    @Expose
    private String hba1c_date;
    @SerializedName("hba1c_ranges")
    @Expose
    private double hba1c_ranges;


    public GetHealthDetailsPayload(int diabetic_patient_id, int age, String gender, double height, double weight, String improvement_status, String disease_category, String hba1c_date, double hba1c_ranges) {
        this.diabetic_patient_id = diabetic_patient_id;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.improvement_status = improvement_status;
        this.disease_category = disease_category;
        this.hba1c_date = hba1c_date;
        this.hba1c_ranges = hba1c_ranges;
    }

    public int getDiabetic_patient_id() {
        return diabetic_patient_id;
    }

    public void setDiabetic_patient_id(int diabetic_patient_id) {
        this.diabetic_patient_id = diabetic_patient_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getImprovement_status() {
        return improvement_status;
    }

    public void setImprovement_status(String improvement_status) {
        this.improvement_status = improvement_status;
    }

    public String getDisease_category() {
        return disease_category;
    }

    public void setDisease_category(String disease_category) {
        this.disease_category = disease_category;
    }

    public String getHba1c_date() {
        return hba1c_date;
    }

    public void setHba1c_date(String hba1c_date) {
        this.hba1c_date = hba1c_date;
    }

    public double getHba1c_ranges() {
        return hba1c_ranges;
    }

    public void setHba1c_ranges(double hba1c_ranges) {
        this.hba1c_ranges = hba1c_ranges;
    }


    @Override
    public String toString() {
        return "GetHealthDetailsPayload{" +
                "diabetic_patient_id=" + diabetic_patient_id +
                ", age=" + age +
                ", gender=" + gender +
                ", height=" + height +
                ", weight=" + weight +
                ", improvement_status='" + improvement_status + '\'' +
                ", disease_category='" + disease_category + '\'' +
                ", hba1c_date='" + hba1c_date + '\'' +
                ", hba1c_ranges=" + hba1c_ranges +
                '}';
    }
}

