package com.fightpandemics.ui.vminject

import androidx.lifecycle.ViewModelProvider
import com.fightpandemics.ui.login.data.LoginDataSource
import com.fightpandemics.ui.login.data.LoginRepository
import com.fightpandemics.ui.login.viewmodel.LoginViewModelFactory

object Injection {

    //Todo - Add all ViewModelFactory providers can be added here


    private val loginDataSource: LoginDataSource = LoginRepository()
    private val loginViewModelFactory = LoginViewModelFactory(loginDataSource)

    //provideDataSource method can also be added here if needed
    
    fun provideLoginViewModelFactory(): ViewModelProvider.Factory{
        return loginViewModelFactory
    }
}