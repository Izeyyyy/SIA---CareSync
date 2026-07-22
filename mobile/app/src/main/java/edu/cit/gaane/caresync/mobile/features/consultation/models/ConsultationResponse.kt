package edu.cit.gaane.caresync.mobile.features.consultation.models

data class ConsultationResponse(

    val id: Long,

    val patientId: Long,

    val patientName: String,

    val doctorId: Long,

    val doctorName: String,

    val chiefComplaint: String,

    val diagnosis: String,

    val treatmentPlan: String,

    val prescription: String,

    val notes: String,

    val consultationDate: String

)