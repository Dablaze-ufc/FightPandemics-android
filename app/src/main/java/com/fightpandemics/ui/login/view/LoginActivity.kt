package com.fightpandemics.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fightpandemics.R
import com.fightpandemics.home.HomeActivity
import com.fightpandemics.ui.login.model.LoginRequest
import com.fightpandemics.ui.login.model.LoginResponse
import com.fightpandemics.ui.login.viewmodel.LoginViewModel
import com.fightpandemics.ui.vminject.Injection
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupViewModel()

        login_btn.setOnClickListener {

            //Todo - This should be handled elegantly. This is a quick fix

            if(etIsEmpty()){

                var loginReq = LoginRequest(et_email.text.toString(),et_pswd.text.toString())
                viewModel.loginToFp(loginReq)
            }
            else  if (et_email.text.isNullOrEmpty() || et_email.text.isNullOrBlank()){
                Toast.makeText(this, "Please enter your registered email ID", Toast.LENGTH_LONG).show()

                emailLyt.error = "Please enter your Email ID"
                emailLyt.isErrorEnabled = true

            }
            else if(et_pswd.text.isNullOrEmpty() || et_pswd.text.isNullOrBlank()){

                Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show()

                pswdLyt.error = "Please enter your password"
                pswdLyt.isErrorEnabled = true

            }

        }
    }

    private fun etIsEmpty(): Boolean {
        if (et_email.text.isNullOrEmpty() || et_email.text.isNullOrBlank()) return false
        if (et_pswd.text.isNullOrEmpty() || et_pswd.text.isNullOrBlank()) return false
        return true
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            Injection.provideLoginViewModelFactory()
        ).get(LoginViewModel::class.java)

        viewModel.loginResponse.observe(this, loginObserver)

        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isEmailVerified.observe(this, noEmailObserver)
    }

    private val loginObserver = Observer<LoginResponse> {
        Log.v("Data Recieved", "Response $it")

        intent = Intent(applicationContext, HomeActivity::class.java)
        startActivity(intent)

    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("Loading", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    //Todo : Error handling is not working. Needs to be fixed
    private val onMessageErrorObserver = Observer<Any> {
        Log.v("Error", "onMessageError $it")

        Toast.makeText(this, "Login Error $it", Toast.LENGTH_LONG).show()

        val visibility = if (it!=null) View.VISIBLE else View.GONE

        progressBar.visibility = visibility


    }

    private val noEmailObserver = Observer<Boolean> {
        Log.v("Is Email Verified: ", "$it")

        val visibility = if (it!=null) View.VISIBLE else View.GONE

        progressBar.visibility = visibility

        Toast.makeText(this, "Your email is not verified", Toast.LENGTH_LONG).show()

    }

}
