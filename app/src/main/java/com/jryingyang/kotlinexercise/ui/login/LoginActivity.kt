package com.jryingyang.kotlinexercise.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jryingyang.kotlinexercise.R
import com.jryingyang.kotlinexercise.databinding.ActivityLoginBinding
import com.jryingyang.kotlinexercise.model.ResponseLogin
import com.jryingyang.kotlinexercise.network.Status
import com.jryingyang.kotlinexercise.ui.base.BaseAppCompatActivity

class LoginActivity : BaseAppCompatActivity<ActivityLoginBinding>() {

    private lateinit var loginViewModel: LoginViewModel

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tilAccount = binding.tilAccount
        val tilPassword = binding.tilPassword
        val username = binding.edtAccount
        val password = binding.edtPassword
        val btnLogin = binding.btnLogin
        val loading = binding.loading

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            btnLogin.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                tilAccount.error = getString(loginState.usernameError)
            } else {
                tilAccount.error = null
            }
            if (loginState.passwordError != null) {
                tilPassword.error = getString(loginState.passwordError)
            } else {
                tilPassword.error = null
            }
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        btnLogin.setOnClickListener {
            loading.visibility = View.VISIBLE
            btnLogin.isClickable = false
            loginViewModel.login(
                getString(R.string.application_id),
                username.text.toString(),
                password.text.toString()
            ).observe(this@LoginActivity, Observer {
                loading.visibility = View.GONE
                btnLogin.isClickable = true
                when (it.status) {
                    Status.SUCCESS -> showLoginSuccess(it.data ?: return@Observer)
                    Status.ERROR -> showLoginFailed(it.message)
                }
            })
        }
    }

    private fun showLoginSuccess(responseLogin: ResponseLogin) {

    }

    private fun showLoginFailed(errorString: String?) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.login_failed))
            .setMessage(errorString)
            .setPositiveButton(getString(R.string.confirm), null)
            .show()
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}