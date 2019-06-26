package com.example.testapp.userinfo.API

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitBuilder private constructor() {
    private val retrofit: Retrofit
    val userAPI:UserAPI
    get(){
        return retrofit.create(UserAPI::class.java)
    }
    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor)
        val gson = GsonBuilder().setLenient().create()
        retrofit = Retrofit.Builder().baseUrl(Companion.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).client(client.build())
            .build()
    }

    companion object {
        private lateinit var retrofitBuilder:RetrofitBuilder
        val instance: RetrofitBuilder
            get(){
//                if (retrofitBuilder == null){
//                    retrofitBuilder = RetrofitBuilder()
//                }
                retrofitBuilder = RetrofitBuilder()
                return retrofitBuilder
            }
        const val BASE_URL = "https://randomuser.me"
    }
}