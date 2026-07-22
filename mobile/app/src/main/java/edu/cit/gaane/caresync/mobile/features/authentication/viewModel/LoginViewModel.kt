package edu.cit.gaane.caresync.mobile.features.authentication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.cit.gaane.caresync.mobile.features.authentication.repository.AuthenticationRepository
import edu.cit.gaane.caresync.mobile.features.authentication.models.LoginResponse
import edu.cit.gaane.caresync.mobile.shared.storage.SessionProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class LoginViewModel : ViewModel() {


    private val repository =
        AuthenticationRepository()



    private val _loggedInUser =
        MutableStateFlow<LoginResponse?>(null)


    private val _loginResult =
        MutableStateFlow<String?>(null)


    private val _isLoading =
        MutableStateFlow(false)



    val loggedInUser: StateFlow<LoginResponse?>
        get() = _loggedInUser



    val loginResult: StateFlow<String?>
        get() = _loginResult



    val isLoading: StateFlow<Boolean>
        get() = _isLoading




    fun login(
        email: String,
        password: String
    ) {


        viewModelScope.launch {


            _isLoading.value = true


            try {


                val user =
                    repository.login(
                        email,
                        password
                    )


                SessionProvider.sessionManager.saveSession(

                    token = user.token,

                    role = user.role,

                    email = user.email,

                    firstName = user.firstName

                )

                _loggedInUser.value = user

                _loginResult.value =
                    "Login successful"



            }


            catch (e: HttpException) {


                when(e.code()) {


                    401 -> {

                        _loginResult.value =
                            "Invalid email or password"

                    }


                    409 -> {

                        _loginResult.value =
                            "Account already exists"

                    }


                    else -> {

                        _loginResult.value =
                            "Something went wrong"

                    }

                }


            }


            catch(e: IOException) {


                _loginResult.value =
                    "Cannot connect to server"


            }


            catch(e: Exception) {


                _loginResult.value =
                    e.message ?: "Login failed"


            }


            finally {


                _isLoading.value = false


            }

        }

    }

}