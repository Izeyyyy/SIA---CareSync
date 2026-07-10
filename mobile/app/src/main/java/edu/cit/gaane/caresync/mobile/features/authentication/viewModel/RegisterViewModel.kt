package edu.cit.gaane.caresync.mobile.features.authentication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.cit.gaane.caresync.mobile.features.authentication.model.RegisterRequest
import edu.cit.gaane.caresync.mobile.features.authentication.repository.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val repository =
            AuthenticationRepository()


    private val _registerResult =
            MutableStateFlow<String?>(null)


    val registerResult: StateFlow<String?>
        get() = _registerResult



    fun register(
            firstName: String,
            lastName: String,
            middleInitial: String?,
            email: String,
            password: String,
            role: String
    ) {

        viewModelScope.launch {

            try {

                val request =
                        RegisterRequest(
                                firstName = firstName,
                                lastName = lastName,
                                middleInitial = middleInitial,
                                email = email,
                                password = password,
                                role = role
                        )


                repository.register(request)


                _registerResult.value =
                        "Registration successful"


            } catch (e: Exception) {

                _registerResult.value =
                        "Registration failed"

            }
        }
    }
}