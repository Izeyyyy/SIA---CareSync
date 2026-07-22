package edu.cit.gaane.caresync.mobile.features.patients.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.cit.gaane.caresync.mobile.features.patients.models.Patient
import edu.cit.gaane.caresync.mobile.features.patients.repository.PatientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PatientDetailsViewModel : ViewModel() {

    private val repository = PatientRepository()

    private val _patient =

        MutableStateFlow<Patient?>(null)

    val patient: StateFlow<Patient?>

        get() = _patient

    fun loadPatient(id: Long) {

        viewModelScope.launch {

            try {

                _patient.value =

                    repository.getPatient(id)

            }

            catch (e: Exception) {

                e.printStackTrace()

            }

        }

    }

}