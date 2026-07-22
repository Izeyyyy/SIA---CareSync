package edu.cit.gaane.caresync.mobile.features.patients.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.cit.gaane.caresync.mobile.features.patients.models.PatientRequest
import edu.cit.gaane.caresync.mobile.features.patients.viewmodel.RegisterPatientViewModel
import edu.cit.gaane.caresync.mobile.shared.components.*

@Composable
fun RegisterPatientScreen(
    onFinished: () -> Unit,
    onBackClick: () -> Unit,
    vm: RegisterPatientViewModel = viewModel()
) {
    var firstName by remember { mutableStateOf("") }
    var middleInitial by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }
    var genderExpanded by remember { mutableStateOf(false) }
    var contact by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    val success by vm.success.collectAsState()

    LaunchedEffect(success) {
        if (success) onFinished()
    }

    CareSyncScaffold(
        title = "Register Patient",
        onBackClick = onBackClick
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Personal Information")
            
            CareSyncTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = "First Name"
            )
            Spacer(Modifier.height(12.dp))
            
            Row(modifier = Modifier.fillMaxWidth()) {
                CareSyncTextField(
                    value = middleInitial,
                    onValueChange = { if (it.length <= 1) middleInitial = it },
                    label = "M.I.",
                    modifier = Modifier.weight(0.3f)
                )
                Spacer(Modifier.width(12.dp))
                CareSyncTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = "Last Name",
                    modifier = Modifier.weight(0.7f)
                )
            }
            Spacer(Modifier.height(12.dp))

            CareSyncTextField(
                value = birthDate,
                onValueChange = { birthDate = it },
                label = "Birth Date",
                placeholder = "MM/DD/YYYY"
            )
            Spacer(Modifier.height(16.dp))

            Text(
                text = "Gender",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Box {
                CareSyncOutlinedButton(
                    text = gender,
                    onClick = { genderExpanded = true }
                )
                DropdownMenu(
                    expanded = genderExpanded,
                    onDismissRequest = { genderExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Male") },
                        onClick = { gender = "Male"; genderExpanded = false }
                    )
                    DropdownMenuItem(
                        text = { Text("Female") },
                        onClick = { gender = "Female"; genderExpanded = false }
                    )
                }
            }

            Spacer(Modifier.height(32.dp))
            SectionHeader("Contact & Address")

            CareSyncTextField(
                value = contact,
                onValueChange = { contact = it },
                label = "Contact Number"
            )
            Spacer(Modifier.height(12.dp))

            CareSyncTextField(
                value = address,
                onValueChange = { address = it },
                label = "Complete Address"
            )

            Spacer(Modifier.height(48.dp))

            CareSyncButton(
                text = "Save Patient Record",
                onClick = {
                    val formattedBirthDate = birthDate.split("/").let {
                        if (it.size == 3) "${it[2]}-${it[0]}-${it[1]}" else birthDate
                    }
                    vm.register(
                        PatientRequest(
                            firstName,
                            middleInitial.firstOrNull() ?: ' ',
                            lastName,
                            formattedBirthDate,
                            gender,
                            contact,
                            address
                        )
                    )
                }
            )
            
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}
