package com.example.hostelmealmanagement.expense;

import com.example.hostelmealmanagement.expense.create_expense.DeleteExpenseData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteExpenseDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private DeleteExpenseData deleteExpenseData;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DeleteExpenseData getDeleteExpenseData() {
        return deleteExpenseData;
    }

    public void setDeleteExpenseData(DeleteExpenseData deleteExpenseData) {
        this.deleteExpenseData = deleteExpenseData;
    }
}
