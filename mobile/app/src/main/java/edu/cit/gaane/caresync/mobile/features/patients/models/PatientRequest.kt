package edu.cit.gaane.caresync.mobile.features.patients.models

data class PatientRequest(

    val firstName: String,

    val middleInitial: Char,

    val lastName: String,

    val birthDate: String,

    val gender: String,

    val contactNumber: String,

    val address: String

)