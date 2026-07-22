package edu.cit.gaane.caresync.mobile.features.patients.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.cit.gaane.caresync.mobile.features.patients.models.PatientRequest
import edu.cit.gaane.caresync.mobile.features.patients.viewmodel.EditPatientViewModel
import edu.cit.gaane.caresync.mobile.shared.components.*



@Composable
fun EditPatientScreen(

    patientId: Long,

    onFinished: () -> Unit,

    onBackClick: () -> Unit,

    vm: EditPatientViewModel = viewModel()

) {


    val patient by vm.patient.collectAsState()


    val success by vm.success.collectAsState()



    var firstName by remember {
        mutableStateOf("")
    }

    var middleInitial by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var birthDate by remember {
        mutableStateOf("")
    }

    var gender by remember {
        mutableStateOf("")
    }

    var contact by remember {
        mutableStateOf("")
    }

    var address by remember {
        mutableStateOf("")
    }



    LaunchedEffect(Unit){

        vm.loadPatient(patientId)

    }



    LaunchedEffect(patient){

        patient?.let {


            firstName = it.firstName

            middleInitial =
                it.middleInitial.toString()

            lastName = it.lastName

            birthDate = it.birthDate

            gender = it.gender

            contact = it.contactNumber

            address = it.address


        }


    }



    LaunchedEffect(success){

        if(success)
            onFinished()

    }



    CareSyncScaffold(

        title = "Edit Patient",

        onBackClick = onBackClick

    ){


        Column(

            modifier = Modifier

                .padding(24.dp)

                .verticalScroll(
                    rememberScrollState()
                )

        ){


            CareSyncTextField(

                value = firstName,

                onValueChange = {
                    firstName = it
                },

                label = "First Name"

            )


            Spacer(
                Modifier.height(12.dp)
            )


            CareSyncTextField(

                value = middleInitial,

                onValueChange = {
                    middleInitial = it
                },

                label = "Middle Initial"

            )


            Spacer(
                Modifier.height(12.dp)
            )


            CareSyncTextField(

                value = lastName,

                onValueChange = {
                    lastName = it
                },

                label = "Last Name"

            )


            Spacer(
                Modifier.height(12.dp)
            )


            CareSyncTextField(

                value = birthDate,

                onValueChange = {
                    birthDate = it
                },

                label = "Birth Date"

            )


            Spacer(
                Modifier.height(12.dp)
            )


            CareSyncTextField(

                value = gender,

                onValueChange = {
                    gender = it
                },

                label = "Gender"

            )


            Spacer(
                Modifier.height(12.dp)
            )


            CareSyncTextField(

                value = contact,

                onValueChange = {
                    contact = it
                },

                label = "Contact Number"

            )


            Spacer(
                Modifier.height(12.dp)
            )


            CareSyncTextField(

                value = address,

                onValueChange = {
                    address = it
                },

                label = "Address"

            )


            Spacer(
                Modifier.height(24.dp)
            )


            CareSyncButton(

                text = "Save Changes",

                onClick = {


                    vm.updatePatient(

                        patientId,

                        PatientRequest(

                            firstName,

                            middleInitial.firstOrNull()
                                ?: ' ',

                            lastName,

                            birthDate,

                            gender,

                            contact,

                            address

                        )

                    )


                }

            )


        }


    }


}