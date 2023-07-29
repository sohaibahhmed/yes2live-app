package com.fyp.yes2live.model;


import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class DiabeticPatient {
    @SerializedName("user_id")
    private long user_id;
    @SerializedName("blood_pressure")
    private boolean blood_pressure;
    @SerializedName("cholesterol")
    private boolean cholesterol;
    @SerializedName("diabetic_family")
    private boolean diabetic_family;
    @SerializedName("hba1c_date")
    private String hba1c_date;
    @SerializedName("hba1c_ranges")
    private double hba1c_ranges;
    @SerializedName("systolic_ranges")
    private int systolic_ranges;
    @SerializedName("diastolic_ranges")
    private int diastolic_ranges;
    @SerializedName("cholesterol_ranges")
    private int cholesterol_ranges;
    @SerializedName("married")
    private boolean married;
    @SerializedName("other_diseases")
    private boolean other_diseases;
    @SerializedName("pregnant")
    private boolean pregnant;
    @SerializedName("no_diseases")
    private boolean no_diseases;


    public DiabeticPatient(long user_id, boolean blood_pressure, boolean cholesterol,
                           boolean diabetic_family, String hba1c_date, double hba1c_ranges,
                           int systolic_ranges, int diastolic_ranges, int cholesterol_ranges,
                           boolean married, boolean other_diseases, boolean pregnant, boolean no_diseases) {
        this.user_id = user_id;
        this.blood_pressure = blood_pressure;
        this.cholesterol = cholesterol;
        this.diabetic_family = diabetic_family;
        this.hba1c_date = hba1c_date;
        this.hba1c_ranges = hba1c_ranges;
        this.systolic_ranges = systolic_ranges;
        this.diastolic_ranges = diastolic_ranges;
        this.cholesterol_ranges = cholesterol_ranges;
        this.married = married;
        this.other_diseases = other_diseases;
        this.pregnant = pregnant;
        this.no_diseases = no_diseases;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public boolean isBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(boolean blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public boolean isCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(boolean cholesterol) {
        this.cholesterol = cholesterol;
    }

    public boolean isDiabetic_family() {
        return diabetic_family;
    }

    public void setDiabetic_family(boolean diabetic_family) {
        this.diabetic_family = diabetic_family;
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

    public int getSystolic_ranges() {
        return systolic_ranges;
    }

    public void setSystolic_ranges(int systolic_ranges) {
        this.systolic_ranges = systolic_ranges;
    }

    public int getDiastolic_ranges() {
        return diastolic_ranges;
    }

    public void setDiastolic_ranges(int diastolic_ranges) {
        this.diastolic_ranges = diastolic_ranges;
    }

    public int getCholesterol_ranges() {
        return cholesterol_ranges;
    }

    public void setCholesterol_ranges(int cholesterol_ranges) {
        this.cholesterol_ranges = cholesterol_ranges;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public boolean isOther_diseases() {
        return other_diseases;
    }

    public void setOther_diseases(boolean other_diseases) {
        this.other_diseases = other_diseases;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public boolean isNo_diseases() {
        return no_diseases;
    }

    public void setNo_diseases(boolean no_diseases) {
        this.no_diseases = no_diseases;
    }
}
