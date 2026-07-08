package edu.cit.gaane.caresync.mobile.features.authentication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.cit.gaane.caresync.mobile.features.authentication.repository.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import edu.cit.gaane.caresync.mobile.features.authentication.model.LoginResponse

class LoginViewModel : ViewModel() {

    private val repository =
            AuthenticationRepository()


    private val _loggedInUser =
            MutableStateFlow<LoginResponse?>(null)

    private val _loginResult =
            MutableStateFlow<String?>(null)

    val loggedInUser: StateFlow<LoginResponse?>
        get() = _loggedInUser


    val loginResult: StateFlow<String?>
        get() = _loginResult



    fun login(
            email: String,
            password: String
    ) {

        viewModelScope.launch {

            try {

                val user =
                        repository.login(email, password)

                _loggedInUser.value = user

                _loginResult.value =
                        "Login successful"


            } catch (e: Exception) {

                _loginResult.value =
                        "Server connection failed"

            }
        }
    }
}