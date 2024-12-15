package com.example.news.api

import com.example.news.models.LoginRequest
import com.example.news.models.LoginResponse
import com.example.news.models.RegisterRequest
import com.example.news.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register.php")

    fun register(
        @Field("username") username:String,
        @Field("password") password:String,
        @Field("fullname") fullname:String,
        @Field("email") email:String
    ):Call<RegisterResponse>

//    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("login.php")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}