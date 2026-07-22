package edu.cit.gaane.caresync.mobile.features.consultation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.cit.gaane.caresync.mobile.features.consultation.models.ConsultationRequest
import edu.cit.gaane.caresync.mobile.features.consultation.models.ConsultationResponse
import edu.cit.gaane.caresync.mobile.features.consultation.repository.ConsultationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConsultationViewModel : ViewModel() {

    private val repository = ConsultationRepository()

    private val _consultations =
        MutableStateFlow<List<ConsultationResponse>>(emptyList())

    private val _consultation =
        MutableStateFlow<ConsultationResponse?>(null)

    val consultation =
        _consultation.asStateFlow()

    val consultations =
        _consultations.asStateFlow()

    private val _success =
        MutableStateFlow(false)

    val success =
        _success.asStateFlow()

    fun loadConsultations(patientId: Long) {

        viewModelScope.launch {

            _consultations.value =
                repository.getPatientConsultations(patientId)

        }

    }

    fun createConsultation(request: ConsultationRequest) {

        viewModelScope.launch {

            repository.createConsultation(request)

            _success.value = true

        }

    }

    fun loadConsultation(

        consultationId: Long

    ) {

        viewModelScope.launch {

            _consultation.value =
                repository.getConsultation(consultationId)

        }

    }

}