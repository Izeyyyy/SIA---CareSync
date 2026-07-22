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
import edu.cit.gaane.caresync.mobile.features.consultation.viewmodel.ConsultationViewModel
import edu.cit.gaane.caresync.mobile.shared.components.*

@Composable
fun ConsultationDetailsScreen(
    consultationId: Long,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit,
    consultationViewModel: ConsultationViewModel = viewModel()
) {
    val consultation by consultationViewModel.consultation.collectAsState()

    LaunchedEffect(Unit) {
        consultationViewModel.loadConsultation(consultationId)
    }

    CareSyncScaffold(
        title = "Consultation Record",
        onBackClick = onBackClick
    ) { paddingValues ->
        if (consultation == null) {
            CareSyncLoading()
        } else {
            consultation?.let { c ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp)
                ) {
                    // Patient & Doctor Info
                    CareSyncCard {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            InfoSection("Patient", c.patientName, Modifier.weight(1f))
                            InfoSection("Date", c.consultationDate, Modifier.weight(1f))
                        }
                        Spacer(Modifier.height(16.dp))
                        InfoSection("Attending Physician", "Dr. ${c.doctorName}")
                    }

                    Spacer(Modifier.height(24.dp))

                    // Medical Content
                    MedicalSection("Chief Complaint", c.chiefComplaint)
                    MedicalSection("Diagnosis", c.diagnosis)
                    MedicalSection("Treatment Plan", c.treatmentPlan)
                    MedicalSection("Prescription", c.prescription)
                    MedicalSection("Clinical Notes", c.notes)
                    
                    Spacer(Modifier.height(16.dp))

                    CareSyncButton(
                        text = "Edit Consultation",
                        onClick = onEditClick
                    )

                    Spacer(Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
private fun InfoSection(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun MedicalSection(title: String, content: String) {
    Column(modifier = Modifier.padding(bottom = 20.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        CareSyncCard(padding = 12.dp) {
            Text(
                text = content.ifBlank { "N/A" },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
