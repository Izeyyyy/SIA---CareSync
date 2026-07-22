package edu.cit.gaane.caresync.mobile.features.consultation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.cit.gaane.caresync.mobile.features.consultation.viewmodel.ConsultationViewModel
import edu.cit.gaane.caresync.mobile.shared.components.*

@Composable
fun PatientConsultationListScreen(
    patientId: Long,
    onNewConsultation: () -> Unit,
    onConsultationClick: (Long) -> Unit,
    onBackClick: () -> Unit,
    consultationViewModel: ConsultationViewModel = viewModel()
) {
    val consultations by consultationViewModel.consultations.collectAsState()

    LaunchedEffect(Unit) {
        consultationViewModel.loadConsultations(patientId)
    }

    CareSyncScaffold(
        title = "Consultation History",
        onBackClick = onBackClick,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNewConsultation,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "New Consultation")
            }
        }
    ) { paddingValues ->
        if (consultations.isEmpty()) {
            CareSyncEmptyState(message = "No consultations found for this patient.")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(consultations) { consultation ->
                    ConsultationCard(
                        date = consultation.consultationDate,
                        doctor = consultation.doctorName,
                        complaint = consultation.chiefComplaint,
                        diagnosis = consultation.diagnosis,
                        onClick = { onConsultationClick(consultation.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ConsultationCard(
    date: String,
    doctor: String,
    complaint: String,
    diagnosis: String,
    onClick: () -> Unit
) {
    CareSyncCard(
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = date,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(Modifier.height(8.dp))
                
                Text(
                    text = complaint,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Text(
                    text = diagnosis,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(Modifier.height(12.dp))
                
                Text(
                    text = "Doctor: $doctor",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
