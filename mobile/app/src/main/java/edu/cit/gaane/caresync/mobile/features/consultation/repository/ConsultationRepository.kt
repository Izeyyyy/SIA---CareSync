package edu.cit.gaane.caresync.mobile.features.consultation.repository

import edu.cit.gaane.caresync.mobile.features.authentication.network.RetrofitInstance
import edu.cit.gaane.caresync.mobile.features.consultation.models.ConsultationRequest

class ConsultationRepository {

    suspend fun createConsultation(

        request: ConsultationRequest

    ) = RetrofitInstance.consultationApi.createConsultation(request)

    suspend fun getPatientConsultations(

        patientId: Long

    ) = RetrofitInstance.consultationApi.getPatientConsultations(patientId)

    suspend fun getConsultation(

        consultationId: Long

    ) = RetrofitInstance.consultationApi.getConsultation(consultationId)

    suspend fun updateConsultation(

        consultationId: Long,

        request: ConsultationRequest

    ) = RetrofitInstance.consultationApi.updateConsultation(
        consultationId,
        request
    )

}