package com.example.hostelmealmanagement.retrofit;

import com.example.hostelmealmanagement.deposit.TotalDepositAmountDataResponse;
import com.example.hostelmealmanagement.homePageDataResponse;
import com.example.hostelmealmanagement.login.LogInGetDataResponse;
import com.example.hostelmealmanagement.login.LogInSetDataResponse;
import com.example.hostelmealmanagement.register.RegisterGetDataResponse;
import com.example.hostelmealmanagement.register.RegisterSetDataResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    //registration
    @POST("api/auth/register")
    Call<RegisterGetDataResponse> registrationData(@Body RegisterSetDataResponse registerSetDataResponse);

    //logIn
    @POST("api/auth/login")
    Call<LogInGetDataResponse> logInData(@Body LogInSetDataResponse logInSetDataResponse);

    //deposit
    @GET("api/deposit/")
    Call<homePageDataResponse> totalDepositAmount(@Header("Authorization") String authorization);

    //Expenses
    //Total Expense Cost
    @GET("api/expenses/cost")
    Call<homePageDataResponse> totalExpenseCost(@Header("Authorization") String authorization);


    //meal
    //Total meal
    @GET("api/meal")
    Call<homePageDataResponse> totalMealCount(@Header("Authorization") String authorization);


    // calculation
    //get meal rate
    @GET("api/meal")
    Call<homePageDataResponse> mealRate(@Header("Authorization") String authorization);


}
