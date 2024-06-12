package com.example.userfeature.presenter.user.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.userfeature.R
import com.example.userfeature.presenter.activity.HomeActivity
import com.example.userfeature.presenter.user.intent.LoginIntent
import com.example.userfeature.presenter.user.state.UserUIState
import com.example.userfeature.presenter.user.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(
            R.layout.fragment_login, container, false
        )

        val edtUsername = view.findViewById<EditText>(R.id.username)
        val edtPassword = view.findViewById<EditText>(R.id.password)
        val btnLogin = view.findViewById<Button>(R.id.login)
        val btnRegister = view.findViewById<Button>(R.id.register)
        val prgLoading = view.findViewById<ProgressBar>(R.id.loading)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        edtUsername.addTextChangedListener {
            viewModel.processIntent(LoginIntent.UsernameChanged(it.toString()))
        }

        edtPassword.addTextChangedListener {
            viewModel.processIntent(LoginIntent.PasswordChanged(it.toString()))
        }

        btnLogin.setOnClickListener {
            viewModel.processIntent(LoginIntent.SubmitLogin)
        }

        btnRegister.setOnClickListener {
            viewModel.processIntent(LoginIntent.RegisterUser)
        }

        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            if (state.usernameError) {
                edtUsername.error = getString(R.string.invalid_email_error_text)
            }
            if (state.passwordError) {
                edtPassword.error = getString(R.string.password_is_empty_error_text)
            }
        }

        viewModel.signInState.observe(viewLifecycleOwner) {
            when (it) {
                is UserUIState.ShowUserError -> handleError()
                is UserUIState.ShowUserAlreadyExistError -> handleUserAlreadyExistError()
                is UserUIState.Loading -> prgLoading.visibility =
                    if (it.isLoading) View.VISIBLE else View.GONE

                UserUIState.NavigateToAfterLogin -> (activity as? HomeActivity)?.navigateToInspectionScreen()
            }
        }

        return view
    }

    private fun handleUserAlreadyExistError() {
        Toast.makeText(
            activity,
            getString(R.string.user_already_existed_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun handleError() {
        Toast.makeText(
            activity,
            getString(R.string.invalid_credentials_error),
            Toast.LENGTH_SHORT
        ).show()
    }
}
