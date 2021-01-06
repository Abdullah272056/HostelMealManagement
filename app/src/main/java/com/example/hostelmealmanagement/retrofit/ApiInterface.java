package com.example.hostelmealmanagement.retrofit;

import com.example.hostelmealmanagement.calculation.GetAllCalculationDataResponse;
import com.example.hostelmealmanagement.expense.DeleteExpenseDataResponse;
import com.example.hostelmealmanagement.expense.GetAllExpenseDataResponse;
import com.example.hostelmealmanagement.expense.create_expense.CreateExpenseGateDataResponse;
import com.example.hostelmealmanagement.expense.create_expense.CreateExpenseSetData;
import com.example.hostelmealmanagement.get_all_member.GetAllMemberDataResponse;
import com.example.hostelmealmanagement.HomePageDataResponse;
import com.example.hostelmealmanagement.login.LogInGetDataResponse;
import com.example.hostelmealmanagement.login.LogInSetDataResponse;
import com.example.hostelmealmanagement.meal.MealRateDataResponse;
import com.example.hostelmealmanagement.register.RegisterGetDataResponse;
import com.example.hostelmealmanagement.register.RegisterSetDataResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    //Auth
    //registration
    @POST("api/auth/register")
    Call<RegisterGetDataResponse> registrationData(@Body RegisterSetDataResponse registerSetDataResponse);
    //logIn
    @POST("api/auth/login")
    Call<LogInGetDataResponse> logInData(@Body LogInSetDataResponse logInSetDataResponse);
    // getAll member
    @GET("api/auth/member")
    Call<GetAllMemberDataResponse> getAllMember(@Header("Authorization") String authorization);


    //deposit
    //get all deposit
    @GET("api/deposit/")
    Call<HomePageDataResponse> totalDepositAmount(@Header("Authorization") String authorization);



    //Expenses
    //Total Expense Cost
    @GET("api/expenses/cost")
    Call<HomePageDataResponse> totalExpenseCost(@Header("Authorization") String authorization);
    //get All Expense
    @GET("api/expenses")
    Call<GetAllExpenseDataResponse> getAllExpense(@Header("Authorization") String authorization);
    //POST Add Expenses
    @POST("api/expenses/")
    Call<CreateExpenseGateDataResponse> createExpense(@Header("Authorization") String authorization,@Body CreateExpenseSetData createExpenseSetData);
    //delete Expense
    @DELETE("api/expenses/{id}")
    Call<DeleteExpenseDataResponse> deleteExpense(@Header("Authorization") String authorization, @Path("id") String id);


    //meal
    //Total meal
    @GET("api/meal")
    Call<HomePageDataResponse> totalMealCount(@Header("Authorization") String authorization);


    // calculation
    //get meal rate
    @GET("api/calculation/meal-rate")
    Call<MealRateDataResponse> mealRate(@Header("Authorization") String authorization);
    //GET Final Calculation
    @GET("api/calculation/")
    Call<GetAllCalculationDataResponse> getFinalCalculation(@Header("Authorization") String authorization);


}
