package edu.cit.gaane.caresync.mobile.features.patients.repository

import edu.cit.gaane.caresync.mobile.features.authentication.network.RetrofitInstance
import edu.cit.gaane.caresync.mobile.features.patients.models.Patient
import edu.cit.gaane.caresync.mobile.features.patients.models.PatientRequest

class PatientRepository {

    suspend fun getPatients(): List<Patient> {

        return RetrofitInstance.patientApi.getPatients()

    }

    suspend fun getPatient(

        id: Long

    ): Patient {

        return RetrofitInstance.patientApi.getPatient(id)

    }

    suspend fun registerPatient(

        patient: PatientRequest

    ): Patient {

        return RetrofitInstance.patientApi.registerPatient(patient)

    }

}