package edu.cit.gaane.caresync.mobile.features.authentication.model

data class LoginResponse(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val middleInitial: Char,
        val email: String,
        val role: String
)