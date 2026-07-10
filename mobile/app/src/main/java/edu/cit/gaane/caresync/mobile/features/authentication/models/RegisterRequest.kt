package edu.cit.gaane.caresync.mobile.features.authentication.model

data class RegisterRequest(
        val username: String,
        val password: String,
        val email: String,
        val firstName: String,
        val lastName: String,
        val middleInitial: String?,
        val role: String
)