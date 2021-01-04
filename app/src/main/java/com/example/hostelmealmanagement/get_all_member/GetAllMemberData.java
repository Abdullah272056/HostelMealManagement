package com.example.hostelmealmanagement.get_all_member;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllMemberData {
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("depositAmount")
    @Expose
    private Integer depositAmount;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("hostelName")
    @Expose
    private String hostelName;
    @SerializedName("meal")
    @Expose
    private List<GetAllMemberMealData> getAllMemberMealDataList = null;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Integer depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public List<GetAllMemberMealData> getGetAllMemberMealDataList() {
        return getAllMemberMealDataList;
    }

    public void setGetAllMemberMealDataList(List<GetAllMemberMealData> getAllMemberMealDataList) {
        this.getAllMemberMealDataList = getAllMemberMealDataList;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
