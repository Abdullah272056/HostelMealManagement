package com.example.hostelmealmanagement.expense.create_expense;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateExpenseSetData {
    @SerializedName("marketer")
    @Expose
    private String marketer;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;

    public String getMarketer() {
        return marketer;
    }

    public void setMarketer(String marketer) {
        this.marketer = marketer;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreateExpenseSetData(String marketer, Integer cost, String type, String name) {
        this.marketer = marketer;
        this.cost = cost;
        this.type = type;
        this.name = name;
    }
}
