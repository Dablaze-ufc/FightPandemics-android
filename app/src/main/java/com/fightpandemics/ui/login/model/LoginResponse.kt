package com.fightpandemics.ui.login.model

data class LoginResponse(
    val email: String,
    val emailVerified: Boolean,
    val token: String,
    val user: Any
)