package com.example.hostelmealmanagement.meal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddMealGetDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private AddMealGetBoarderData addMealGetBoarderData;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public AddMealGetBoarderData getAddMealGetBoarderData() {
        return addMealGetBoarderData;
    }

    public void setAddMealGetBoarderData(AddMealGetBoarderData addMealGetBoarderData) {
        this.addMealGetBoarderData = addMealGetBoarderData;
    }
}
