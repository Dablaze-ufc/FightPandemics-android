package com.fightpandemics.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fightpandemics.ui.login.data.OperationCallback
import com.fightpandemics.ui.login.data.LoginDataSource
import com.fightpandemics.ui.login.model.LoginRequest
import com.fightpandemics.ui.login.model.LoginResponse

class LoginViewModel(private val repository: LoginDataSource):ViewModel() {

    private val _loginResponse = MutableLiveData<LoginResponse>().apply { value }
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmailVerified=MutableLiveData<Boolean>()
    val isEmailVerified:LiveData<Boolean> = _isEmailVerified

    fun loginToFp(loginRequest: LoginRequest){
        _isViewLoading.postValue(true)
        repository.login(loginRequest, object:OperationCallback<LoginResponse>{
            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue( error)
            }

            override fun onSuccess(data: LoginResponse?) {
                _isViewLoading.postValue(false)

                if(data!=null){
                    if(!data.emailVerified){
                        _isEmailVerified.postValue(true)
                    }else{
                        _loginResponse.value= data
                    }
                }
            }
        })
    }

}