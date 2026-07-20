package edu.cit.gaane.caresync.mobile.features.authentication.network

import edu.cit.gaane.caresync.mobile.features.authentication.models.LoginRequest
import edu.cit.gaane.caresync.mobile.features.authentication.models.RegisterRequest
import edu.cit.gaane.caresync.mobile.features.authentication.models.LoginResponse
import edu.cit.gaane.caresync.mobile.features.authentication.models.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {

    @POST("auth/login")
    suspend fun login(
            @Body request: LoginRequest
    ): LoginResponse


    @POST("auth/register")
    suspend fun register(
            @Body request: RegisterRequest
    ): User
}