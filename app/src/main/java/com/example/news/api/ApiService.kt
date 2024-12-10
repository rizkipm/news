package com.example.news.api

import com.example.news.models.LoginRequest
import com.example.news.models.LoginResponse
import com.example.news.models.RegisterRequest
import com.example.news.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register.php")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("login.php")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}