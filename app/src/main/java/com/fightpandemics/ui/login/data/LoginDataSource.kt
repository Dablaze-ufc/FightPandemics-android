package com.fightpandemics.ui.login.data

import com.fightpandemics.ui.login.model.LoginRequest
import com.fightpandemics.ui.login.model.LoginResponse

interface LoginDataSource {

    fun login(loginRequest: LoginRequest, callback: OperationCallback<LoginResponse>)
    fun cancel()
}