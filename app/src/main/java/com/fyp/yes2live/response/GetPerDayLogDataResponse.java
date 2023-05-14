package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPerDayLogDataResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("payload")
    @Expose
    private GetPerDayLogDataPayload payload;

    public GetPerDayLogDataResponse(Integer code, String status, String message, GetPerDayLogDataPayload payload) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GetPerDayLogDataPayload getPayload() {
        return payload;
    }

    public void setPayload(GetPerDayLogDataPayload payload) {
        this.payload = payload;
    }
}
