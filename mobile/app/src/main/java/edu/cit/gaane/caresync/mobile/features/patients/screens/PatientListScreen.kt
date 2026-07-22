package edu.cit.gaane.caresync.mobile.features.patients.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.cit.gaane.caresync.mobile.features.patients.models.Patient
import edu.cit.gaane.caresync.mobile.features.patients.viewmodel.PatientViewModel
import edu.cit.gaane.caresync.mobile.shared.components.*

@Composable
fun PatientListScreen(
    onPatientClick: (Long) -> Unit,
    onAddPatientClick: () -> Unit,
    onBackClick: () -> Unit,
    showRegisterButton: Boolean = true,
    patientViewModel: PatientViewModel = viewModel()
) {
    val patients by patientViewModel.patients.collectAsState()
    // Assuming we have loading and error states in ViewModel. 
    // If not, I will stick to existing logic but improved UI.
    // Based on the provided code, there's only 'patients' StateFlow.
    
    LaunchedEffect(Unit) {
        patientViewModel.loadPatients()
    }

    CareSyncScaffold(
        title = "Patients",
        onBackClick = onBackClick,
        floatingActionButton = {
            if (showRegisterButton) {
                FloatingActionButton(
                    onClick = onAddPatientClick,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Patient")
                }
            }
        }
    ) { paddingValues ->
        if (patients.isEmpty()) {
            CareSyncEmptyState(message = "No patients registered yet.")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(patients) { patient ->
                    PatientCard(
                        patient = patient,
                        onClick = { onPatientClick(patient.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun PatientCard(
    patient: Patient,
    onClick: () -> Unit
) {
    CareSyncCard(
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Gender Icon
            Surface(
                shape = MaterialTheme.shapes.small,
                color = if (patient.gender == "Male") 
                    MaterialTheme.colorScheme.primaryContainer 
                else 
                    MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = if (patient.gender == "Male") 
                            Icons.Default.Male 
                        else 
                            Icons.Default.Female,
                        contentDescription = null,
                        tint = if (patient.gender == "Male") 
                            MaterialTheme.colorScheme.primary 
                        else 
                            MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${patient.firstName} ${patient.lastName}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "ID: ${patient.patientNumber}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Text(
                text = patient.gender,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
