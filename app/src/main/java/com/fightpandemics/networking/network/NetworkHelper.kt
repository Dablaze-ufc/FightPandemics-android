package com.fightpandemics.networking.network

import com.fightpandemics.Variables
import com.fightpandemics.networking.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {

    private var servicesApiInterface: ApiService?=null

//    Todo: Add CertPinner for security

    fun build(): ApiService?{
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(Variables.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ApiService::class.java)

        return servicesApiInterface as ApiService
    }

    //    Todo: Remove below interceptor before release

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}