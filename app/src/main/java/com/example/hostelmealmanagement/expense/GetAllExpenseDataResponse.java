package com.example.hostelmealmanagement.expense;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllExpenseDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<GetAllExpenseData> getAllExpenseDataList = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<GetAllExpenseData> getGetAllExpenseDataList() {
        return getAllExpenseDataList;
    }

    public void setGetAllExpenseDataList(List<GetAllExpenseData> getAllExpenseDataList) {
        this.getAllExpenseDataList = getAllExpenseDataList;
    }
}
