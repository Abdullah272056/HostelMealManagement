package com.example.hostelmealmanagement.calculation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllCalculationDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<GetAllCalculationData> getAllCalculationDataList = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<GetAllCalculationData> getGetAllCalculationDataList() {
        return getAllCalculationDataList;
    }

    public void setGetAllCalculationDataList(List<GetAllCalculationData> getAllCalculationDataList) {
        this.getAllCalculationDataList = getAllCalculationDataList;
    }
}
