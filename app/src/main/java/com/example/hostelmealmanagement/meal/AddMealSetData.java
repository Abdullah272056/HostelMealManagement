package com.example.hostelmealmanagement.meal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddMealSetData {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("mealCount")
    @Expose
    private Integer mealCount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMealCount() {
        return mealCount;
    }

    public void setMealCount(Integer mealCount) {
        this.mealCount = mealCount;
    }

    public AddMealSetData(String userId, Integer mealCount) {
        this.userId = userId;
        this.mealCount = mealCount;
    }
}
