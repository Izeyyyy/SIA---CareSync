package edu.cit.gaane.caresync.mobile.features.patients.network

import edu.cit.gaane.caresync.mobile.features.patients.models.Patient
import edu.cit.gaane.caresync.mobile.features.patients.models.PatientRequest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.POST


interface PatientApi {

    @GET("patients")
    suspend fun getPatients(): List<Patient>

    @GET("patients/{id}")
    suspend fun getPatient(

        @Path("id")

        id: Long

    ): Patient

    @POST("patients")
    suspend fun registerPatient(

        @Body patient: PatientRequest

    ): Patient

}