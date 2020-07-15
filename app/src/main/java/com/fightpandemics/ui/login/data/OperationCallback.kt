package com.fightpandemics.ui.login.data

interface OperationCallback<T> {
    fun onSuccess(data:T?)
    fun onError(error:String?)
}