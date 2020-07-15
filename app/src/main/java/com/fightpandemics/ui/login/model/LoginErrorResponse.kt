package com.fightpandemics.ui.login.model

data class LoginErrorResponse(
    val error: String,
    val message: String,
    val statusCode: Int
)