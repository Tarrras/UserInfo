package com.example.testapp.userinfo.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import com.example.testapp.userinfo.objects.ResponseResult

interface UserAPI {
    @GET("/api/?results=10")
    fun getUserList() : Call<ResponseResult>
}