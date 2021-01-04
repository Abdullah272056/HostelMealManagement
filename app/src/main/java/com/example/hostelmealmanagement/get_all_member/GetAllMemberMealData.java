package com.example.hostelmealmanagement.get_all_member;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllMemberMealData {
    @SerializedName("dateAdded")
    @Expose
    private String dateAdded;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("mealCount")
    @Expose
    private Integer mealCount;

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMealCount() {
        return mealCount;
    }

    public void setMealCount(Integer mealCount) {
        this.mealCount = mealCount;
    }
}
