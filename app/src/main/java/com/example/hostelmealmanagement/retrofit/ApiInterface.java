package com.example.hostelmealmanagement.retrofit;

import com.example.hostelmealmanagement.login.LogInGetDataResponse;
import com.example.hostelmealmanagement.login.LogInSetDataResponse;
import com.example.hostelmealmanagement.register.RegisterGetDataResponse;
import com.example.hostelmealmanagement.register.RegisterSetDataResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    //registration
    @POST("api/auth/register")
    Call<RegisterGetDataResponse> registrationData(@Body RegisterSetDataResponse registerSetDataResponse);

    //logIn
    @POST("api/auth/login")
    Call<LogInGetDataResponse> logInData(@Body LogInSetDataResponse logInSetDataResponse);

}
