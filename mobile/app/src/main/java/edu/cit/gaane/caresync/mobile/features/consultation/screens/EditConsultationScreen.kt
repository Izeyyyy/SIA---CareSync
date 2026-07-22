package edu.cit.gaane.caresync.mobile.features.consultation.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.cit.gaane.caresync.mobile.features.consultation.models.ConsultationRequest
import edu.cit.gaane.caresync.mobile.features.consultation.viewmodel.ConsultationViewModel
import edu.cit.gaane.caresync.mobile.shared.components.*



@Composable
fun EditConsultationScreen(

    consultationId: Long,

    patientId: Long,

    onFinished: () -> Unit,

    onBackClick: () -> Unit,

    vm: ConsultationViewModel = viewModel()

){


    val consultation by vm.consultation.collectAsState()

    val success by vm.success.collectAsState()


    var chiefComplaint by remember { mutableStateOf("") }
    var diagnosis by remember { mutableStateOf("") }
    var treatmentPlan by remember { mutableStateOf("") }
    var prescription by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }



    LaunchedEffect(Unit){

        vm.loadConsultation(consultationId)

    }



    LaunchedEffect(consultation){

        consultation?.let {

            chiefComplaint = it.chiefComplaint
            diagnosis = it.diagnosis
            treatmentPlan = it.treatmentPlan
            prescription = it.prescription
            notes = it.notes

        }

    }



    LaunchedEffect(success){

        if(success){

            onFinished()

        }

    }



    CareSyncScaffold(

        title = "Edit Consultation",

        onBackClick = onBackClick

    ){


        Column(

            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(24.dp)

        ){


            CareSyncTextField(
                value = chiefComplaint,
                onValueChange = {
                    chiefComplaint = it
                },
                label = "Chief Complaint"
            )


            Spacer(
                Modifier.height(16.dp)
            )


            CareSyncTextField(
                value = diagnosis,
                onValueChange = {
                    diagnosis = it
                },
                label = "Diagnosis"
            )


            Spacer(
                Modifier.height(16.dp)
            )


            CareSyncTextField(
                value = treatmentPlan,
                onValueChange = {
                    treatmentPlan = it
                },
                label = "Treatment Plan"
            )


            Spacer(
                Modifier.height(16.dp)
            )


            CareSyncTextField(
                value = prescription,
                onValueChange = {
                    prescription = it
                },
                label = "Prescription"
            )


            Spacer(
                Modifier.height(16.dp)
            )


            CareSyncTextField(
                value = notes,
                onValueChange = {
                    notes = it
                },
                label = "Clinical Notes"
            )


            Spacer(
                Modifier.height(32.dp)
            )



            CareSyncButton(

                text = "Save Changes",

                onClick = {

                    vm.updateConsultation(

                        consultationId,

                        ConsultationRequest(

                            patientId,

                            chiefComplaint,

                            diagnosis,

                            treatmentPlan,

                            prescription,

                            notes

                        )

                    )

                }

            )

        }

    }

}