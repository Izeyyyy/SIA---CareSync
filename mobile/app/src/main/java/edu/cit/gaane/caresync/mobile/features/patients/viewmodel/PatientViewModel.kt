package edu.cit.gaane.caresync.mobile.features.patients.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.cit.gaane.caresync.mobile.features.patients.models.Patient
import edu.cit.gaane.caresync.mobile.features.patients.repository.PatientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PatientViewModel : ViewModel() {

    private val repository = PatientRepository()

    private val _patients =

        MutableStateFlow<List<Patient>>(emptyList())

    val patients: StateFlow<List<Patient>>

        get() = _patients

    fun loadPatients() {

        viewModelScope.launch {

            try {

                _patients.value =

                    repository.getPatients()

            }

            catch (e: Exception) {

                e.printStackTrace()

            }

        }

    }

}