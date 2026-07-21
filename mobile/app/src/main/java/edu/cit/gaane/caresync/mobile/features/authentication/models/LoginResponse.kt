package edu.cit.gaane.caresync.mobile.features.authentication.models

data class LoginResponse(
        val token: String,
        val id: Long,
        val firstName: String,
        val lastName: String,
        val middleInitial: Char,
        val email: String,
        val role: String
)