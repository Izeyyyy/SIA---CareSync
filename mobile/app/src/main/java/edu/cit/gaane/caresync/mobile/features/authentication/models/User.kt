package edu.cit.gaane.caresync.mobile.features.authentication.model

data class User(
        val userId: Long?,
        val username: String,
        val email: String,
        val firstName: String,
        val lastName: String,
        val middleInitial: String?,
        val role: String
)