package com.example.hostelmealmanagement.meal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddMealGetBoarderData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("meal")
    @Expose
    private List<AddMealGetMealData> addMealGetMealDataList = null;

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

    public List<AddMealGetMealData> getAddMealGetMealDataList() {
        return addMealGetMealDataList;
    }

    public void setAddMealGetMealDataList(List<AddMealGetMealData> addMealGetMealDataList) {
        this.addMealGetMealDataList = addMealGetMealDataList;
    }
}
