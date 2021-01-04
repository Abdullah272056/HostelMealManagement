package com.example.hostelmealmanagement.get_all_member;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllMemberDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<GetAllMemberData> getAllMemberDataList = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<GetAllMemberData> getGetAllMemberDataList() {
        return getAllMemberDataList;
    }

    public void setGetAllMemberDataList(List<GetAllMemberData> getAllMemberDataList) {
        this.getAllMemberDataList = getAllMemberDataList;
    }
}
