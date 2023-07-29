package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetGraphDataPayload {

    @SerializedName("weight")
    @Expose
    private double weight;
    @SerializedName("hba1c_date")
    @Expose
    private String hba1c_date;
    @SerializedName("hba1c_ranges")
    @Expose
    private double hba1c_ranges;

    public GetGraphDataPayload(double weight, String hba1c_date, double hba1c_ranges) {
        this.weight = weight;
        this.hba1c_date = hba1c_date;
        this.hba1c_ranges = hba1c_ranges;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
}
