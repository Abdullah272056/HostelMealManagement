package com.example.hostelmealmanagement.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogInGetDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("token")
    @Expose
    private String token;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
