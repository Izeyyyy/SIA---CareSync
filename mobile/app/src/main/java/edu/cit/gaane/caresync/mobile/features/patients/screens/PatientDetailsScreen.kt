package edu.cit.gaane.caresync.mobile.features.patients.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.cit.gaane.caresync.mobile.features.patients.viewmodel.PatientDetailsViewModel
import edu.cit.gaane.caresync.mobile.shared.components.*

@Composable
fun PatientDetailsScreen(
    patientId: Long,
    onConsultations: () -> Unit,
    onNewConsultation: () -> Unit,
    onBackClick: () -> Unit,
    patientViewModel: PatientDetailsViewModel = viewModel()
) {
    val patient by patientViewModel.patient.collectAsState()

    LaunchedEffect(Unit) {
        patientViewModel.loadPatient(patientId)
    }

    CareSyncScaffold(
        title = "Patient Profile",
        onBackClick = onBackClick
    ) { paddingValues ->
        if (patient == null) {
            CareSyncLoading()
        } else {
            patient?.let { p ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp)
                ) {
                    // Header Profile Info
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            shape = MaterialTheme.shapes.extraLarge,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(80.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "${p.firstName.firstOrNull() ?: ""}${p.lastName.firstOrNull() ?: ""}",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = "${p.firstName} ${p.lastName}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Patient No: ${p.patientNumber}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Spacer(Modifier.height(32.dp))

                    // Details Card
                    CareSyncCard {
                        InfoRow("Birth Date", p.birthDate)
                        Divider(modifier = Modifier.padding(vertical = 12.dp), thickness = 0.5.dp)
                        InfoRow("Gender", p.gender)
                        Divider(modifier = Modifier.padding(vertical = 12.dp), thickness = 0.5.dp)
                        InfoRow("Contact Number", p.contactNumber)
                        Divider(modifier = Modifier.padding(vertical = 12.dp), thickness = 0.5.dp)
                        InfoRow("Address", p.address)
                        Divider(modifier = Modifier.padding(vertical = 12.dp), thickness = 0.5.dp)
                        InfoRow("Date Registered", p.dateRegistered)
                    }

                    Spacer(Modifier.height(32.dp))

                    // Actions
                    Text(
                        text = "Consultations",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Row(modifier = Modifier.fillMaxWidth()) {
                        ActionButton(
                            text = "History",
                            icon = Icons.Default.History,
                            onClick = onConsultations,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(Modifier.width(16.dp))
                        ActionButton(
                            text = "New Visit",
                            icon = Icons.Default.NoteAdd,
                            onClick = onNewConsultation,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    
                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CareSyncCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
