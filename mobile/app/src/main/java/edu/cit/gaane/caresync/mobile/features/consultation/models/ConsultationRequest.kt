package edu.cit.gaane.caresync.mobile.features.consultation.models

data class ConsultationRequest(

    val patientId: Long,

    val chiefComplaint: String,

    val diagnosis: String,

    val treatmentPlan: String,

    val prescription: String,

    val notes: String

)