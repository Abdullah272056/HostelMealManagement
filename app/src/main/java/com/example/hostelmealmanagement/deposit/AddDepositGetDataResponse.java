package com.example.hostelmealmanagement.deposit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddDepositGetDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private AddDepositGetCustomerData addDepositGetCustomerData;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public AddDepositGetCustomerData getAddDepositGetCustomerData() {
        return addDepositGetCustomerData;
    }

    public void setAddDepositGetCustomerData(AddDepositGetCustomerData addDepositGetCustomerData) {
        this.addDepositGetCustomerData = addDepositGetCustomerData;
    }
}
