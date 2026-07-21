package edu.cit.gaane.caresync.mobile.features.authentication.models

data class RegisterRequest(
        val password: String,
        val email: String,
        val firstName: String,
        val lastName: String,
        val middleInitial: String?,
        val role: String
)