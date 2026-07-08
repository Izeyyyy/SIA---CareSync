package edu.cit.gaane.caresync.mobile.features.authentication.repository

import edu.cit.gaane.caresync.mobile.features.authentication.model.LoginRequest
import edu.cit.gaane.caresync.mobile.features.authentication.model.LoginResponse
import edu.cit.gaane.caresync.mobile.features.authentication.model.RegisterRequest
import edu.cit.gaane.caresync.mobile.features.authentication.model.User
import edu.cit.gaane.caresync.mobile.features.authentication.network.RetrofitInstance

class AuthenticationRepository {

    suspend fun login(
            email: String,
            password: String
    ): LoginResponse {

        return RetrofitInstance.api.login(
                LoginRequest(
                        email = email,
                        password = password
                )
        )

    }

    suspend fun register(
            request: RegisterRequest
    ): User {

        return RetrofitInstance.api.register(request)

    }

}