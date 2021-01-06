package com.example.hostelmealmanagement.deposit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddDepositSetData {
    @SerializedName("depositAmount")
    @Expose
    private Integer depositAmount;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public Integer getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Integer depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AddDepositSetData(Integer depositAmount, String userId) {
        this.depositAmount = depositAmount;
        this.userId = userId;
    }
}
