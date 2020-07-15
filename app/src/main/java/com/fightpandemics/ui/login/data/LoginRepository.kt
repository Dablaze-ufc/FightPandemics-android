package com.fightpandemics.ui.login.data

import android.util.Log
import com.fightpandemics.networking.network.NetworkHelper
import com.fightpandemics.ui.login.model.LoginRequest
import com.fightpandemics.ui.login.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginRepository: LoginDataSource {

    val apiService = NetworkHelper.build()

    private var call:Call<LoginResponse>?=null

    override fun login(loginRequest: LoginRequest, callback: OperationCallback<LoginResponse>) {
        call= apiService?.login(loginRequest)
        call?.enqueue(object :Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.let {
                    if(response.isSuccessful && (it.emailVerified)){
                        Log.v("Data Recieved: ", "data ${it.token}")
                        callback.onSuccess(it)
                    }else{
                        callback.onError(it.toString())
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}