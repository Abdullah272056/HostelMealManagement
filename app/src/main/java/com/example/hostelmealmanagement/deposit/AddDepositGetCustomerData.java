package com.example.hostelmealmanagement.deposit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddDepositGetCustomerData {
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
    private List<AddDepositGetMealData> addDepositGetMealDataList = null;
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

    public List<AddDepositGetMealData> getAddDepositGetMealDataList() {
        return addDepositGetMealDataList;
    }

    public void setAddDepositGetMealDataList(List<AddDepositGetMealData> addDepositGetMealDataList) {
        this.addDepositGetMealDataList = addDepositGetMealDataList;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
