package com.hfdzafrnsyh.mangapid.network

import com.hfdzafrnsyh.mangapid.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient {
    companion object{
        fun getApiService() : ApiInterface{
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                client.connectTimeout(2, TimeUnit.MINUTES)
                client.readTimeout(2, TimeUnit.MINUTES)
            val clientBuild = client.build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuild)
                .build()
           return retrofit.create(ApiInterface::class.java)
        }
    }
}