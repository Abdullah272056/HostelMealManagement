package com.example.hostelmealmanagement.expense;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteExpenseDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private String data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
