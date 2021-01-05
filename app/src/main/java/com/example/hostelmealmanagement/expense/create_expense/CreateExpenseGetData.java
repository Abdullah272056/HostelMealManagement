package com.example.hostelmealmanagement.expense.create_expense;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateExpenseGetData {
    @SerializedName("_id")
    @Expose
    private String id;
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
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("dateAdded")
    @Expose
    private String dateAdded;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
