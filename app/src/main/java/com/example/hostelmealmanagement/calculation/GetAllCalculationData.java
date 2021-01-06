package com.example.hostelmealmanagement.calculation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllCalculationData {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("totalMeal")
    @Expose
    private Integer totalMeal;
    @SerializedName("mealRate")
    @Expose
    private Double mealRate;
    @SerializedName("totalCost")
    @Expose
    private Integer totalCost;
    @SerializedName("depositAmount")
    @Expose
    private Integer depositAmount;
    @SerializedName("dueOrGetReturn")
    @Expose
    private Integer dueOrGetReturn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalMeal() {
        return totalMeal;
    }

    public void setTotalMeal(Integer totalMeal) {
        this.totalMeal = totalMeal;
    }

    public Double getMealRate() {
        return mealRate;
    }

    public void setMealRate(Double mealRate) {
        this.mealRate = mealRate;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Integer depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Integer getDueOrGetReturn() {
        return dueOrGetReturn;
    }

    public void setDueOrGetReturn(Integer dueOrGetReturn) {
        this.dueOrGetReturn = dueOrGetReturn;
    }
}
