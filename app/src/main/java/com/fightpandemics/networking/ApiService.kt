package com.fightpandemics.networking

import com.fightpandemics.ui.login.model.LoginRequest
import com.fightpandemics.ui.login.model.LoginResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    /*Todo - Add all API endpoints in this class*/

    @POST("/api/auth/login")
    fun login(@Body loginReq : LoginRequest): Call<LoginResponse>


}