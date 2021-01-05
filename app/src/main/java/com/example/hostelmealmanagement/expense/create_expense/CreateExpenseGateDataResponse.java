package com.example.hostelmealmanagement.expense.create_expense;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateExpenseGateDataResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private CreateExpenseGetData createExpenseGetData;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public CreateExpenseGetData getCreateExpenseGetData() {
        return createExpenseGetData;
    }

    public void setCreateExpenseGetData(CreateExpenseGetData createExpenseGetData) {
        this.createExpenseGetData = createExpenseGetData;
    }
}
