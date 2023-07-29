package com.fyp.yes2live.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAssessmentResponse {
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
    private UserAssessmentPayload userAssessmentPayload;

    public UserAssessmentResponse(Integer code, String status, String message, UserAssessmentPayload userAssessmentPayload) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.userAssessmentPayload = userAssessmentPayload;
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

    public UserAssessmentPayload getUserAssessmentPayload() {
        return userAssessmentPayload;
    }

    public void setUserAssessmentPayload(UserAssessmentPayload userAssessmentPayload) {
        this.userAssessmentPayload = userAssessmentPayload;
    }

    @Override
    public String toString() {
        return "UserAssessmentResponse{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", userAssessmentPayload=" + userAssessmentPayload +
                '}';
    }
}
