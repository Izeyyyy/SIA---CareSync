package edu.cit.gaane.caresync.mobile.features.consultation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.cit.gaane.caresync.mobile.features.consultation.models.ConsultationRequest
import edu.cit.gaane.caresync.mobile.features.consultation.viewmodel.ConsultationViewModel
import edu.cit.gaane.caresync.mobile.shared.components.*

@Composable
fun RegisterConsultationScreen(
    patientId: Long,
    onFinished: () -> Unit,
    onBackClick: () -> Unit,
    consultationViewModel: ConsultationViewModel = viewModel()
) {
    var chiefComplaint by remember { mutableStateOf("") }
    var diagnosis by remember { mutableStateOf("") }
    var treatmentPlan by remember { mutableStateOf("") }
    var prescription by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val success by consultationViewModel.success.collectAsState()

    LaunchedEffect(success) {
        if (success) {
            onFinished()
        }
    }

    CareSyncScaffold(
        title = "New Consultation",
        onBackClick = onBackClick
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text(
                text = "Clinical Assessment",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            CareSyncTextField(
                value = chiefComplaint,
                onValueChange = { chiefComplaint = it },
                label = "Chief Complaint"
            )
            Spacer(Modifier.height(16.dp))

            CareSyncTextField(
                value = diagnosis,
                onValueChange = { diagnosis = it },
                label = "Diagnosis"
            )
            Spacer(Modifier.height(16.dp))

            CareSyncTextField(
                value = treatmentPlan,
                onValueChange = { treatmentPlan = it },
                label = "Treatment Plan"
            )
            Spacer(Modifier.height(16.dp))

            CareSyncTextField(
                value = prescription,
                onValueChange = { prescription = it },
                label = "Prescription"
            )
            Spacer(Modifier.height(16.dp))

            CareSyncTextField(
                value = notes,
                onValueChange = { notes = it },
                label = "Additional Notes"
            )

            Spacer(Modifier.height(48.dp))

            CareSyncButton(
                text = "Complete Consultation",
                onClick = {
                    consultationViewModel.createConsultation(
                        ConsultationRequest(
                            patientId = patientId,
                            chiefComplaint = chiefComplaint,
                            diagnosis = diagnosis,
                            treatmentPlan = treatmentPlan,
                            prescription = prescription,
                            notes = notes
                        )
                    )
                }
            )
            
            Spacer(Modifier.height(24.dp))
        }
    }
}
