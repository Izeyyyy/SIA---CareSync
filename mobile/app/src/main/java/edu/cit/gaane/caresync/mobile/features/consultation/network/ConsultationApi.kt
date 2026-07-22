package edu.cit.gaane.caresync.mobile.features.consultation.network

import edu.cit.gaane.caresync.mobile.features.consultation.models.ConsultationRequest
import edu.cit.gaane.caresync.mobile.features.consultation.models.ConsultationResponse
import retrofit2.http.*

interface ConsultationApi {

    @POST("consultations")
    suspend fun createConsultation(

        @Body request: ConsultationRequest

    ): ConsultationResponse

    @GET("consultations/patient/{patientId}")
    suspend fun getPatientConsultations(

        @Path("patientId")
        patientId: Long

    ): List<ConsultationResponse>

    @GET("consultations/{id}")
    suspend fun getConsultation(

        @Path("id")
        id: Long

    ): ConsultationResponse

}