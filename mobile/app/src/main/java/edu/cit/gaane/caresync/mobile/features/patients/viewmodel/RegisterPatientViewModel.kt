package edu.cit.gaane.caresync.mobile.features.patients.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.cit.gaane.caresync.mobile.features.patients.models.PatientRequest
import edu.cit.gaane.caresync.mobile.features.patients.repository.PatientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterPatientViewModel : ViewModel() {

    private val repository = PatientRepository()

    private val _success =

        MutableStateFlow(false)

    val success: StateFlow<Boolean>

        get() = _success

    fun register(

        request: PatientRequest

    ) {

        viewModelScope.launch {

            try {

                repository.registerPatient(request)

                _success.value = true

            }

            catch (e: Exception) {

                e.printStackTrace()

            }

        }

    }

}