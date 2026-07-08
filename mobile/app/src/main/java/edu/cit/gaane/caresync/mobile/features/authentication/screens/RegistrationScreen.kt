package edu.cit.gaane.caresync.mobile.features.authentication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


@Composable
fun RegisterScreen(
        onLoginClick: () -> Unit
) {

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var middleInitial by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }


    var selectedRole by remember {
        mutableStateOf("Doctor")
    }

    var dropdownExpanded by remember {
        mutableStateOf(false)
    }


    val scrollState = rememberScrollState()

    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .background(
                            MaterialTheme.colorScheme.background
                    )
                    .verticalScroll(scrollState)
                    .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
                text = "CareSync",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
        )


        Spacer(
                modifier = Modifier.height(6.dp)
        )


        Text(
                text = "Create Account",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
        )


        Spacer(
                modifier = Modifier.height(20.dp)
        )


        Card(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(12.dp),

                colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                )
        ) {


            Column(
                    modifier = Modifier.padding(30.dp)
            ) {


                Text(
                        text = "Register",
                        style = MaterialTheme.typography.titleMedium
                )


                Spacer(
                        modifier = Modifier.height(20.dp)
                )


                OutlinedTextField(
                        value = firstName,
                        onValueChange = {
                            firstName = it
                        },
                        label = {
                            Text("First Name")
                        },
                        modifier = Modifier.fillMaxWidth()
                )


                Spacer(
                        modifier = Modifier.height(10.dp)
                )


                OutlinedTextField(
                        value = lastName,
                        onValueChange = {
                            lastName = it
                        },
                        label = {
                            Text("Last Name")
                        },
                        modifier = Modifier.fillMaxWidth()
                )


                Spacer(
                        modifier = Modifier.height(10.dp)
                )


                OutlinedTextField(
                        value = middleInitial,
                        onValueChange = {
                            middleInitial = it
                        },
                        label = {
                            Text("Middle Initial")
                        },
                        modifier = Modifier.fillMaxWidth()
                )


                Spacer(
                        modifier = Modifier.height(10.dp)
                )


                OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        label = {
                            Text("Email")
                        },
                        modifier = Modifier.fillMaxWidth()
                )


                Spacer(
                        modifier = Modifier.height(10.dp)
                )


                OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                        },
                        label = {
                            Text("Username")
                        },
                        modifier = Modifier.fillMaxWidth()
                )


                Spacer(
                        modifier = Modifier.height(10.dp)
                )


                OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = {
                            Text("Password")
                        },
                        modifier = Modifier.fillMaxWidth()
                )


                Spacer(
                        modifier = Modifier.height(10.dp)
                )


                Text(
                        text = "Role"
                )


                OutlinedButton(
                        onClick = {
                            dropdownExpanded = true
                        },

                        modifier = Modifier.fillMaxWidth()
                ) {
                    Text(selectedRole)
                }


                DropdownMenu(
                        expanded = dropdownExpanded,

                        onDismissRequest = {
                            dropdownExpanded = false
                        }
                ) {


                    DropdownMenuItem(
                            text = {
                                Text("Doctor")
                            },

                            onClick = {
                                selectedRole = "Doctor"
                                dropdownExpanded = false
                            }
                    )


                    DropdownMenuItem(
                            text = {
                                Text("Clinic Staff")
                            },

                            onClick = {
                                selectedRole = "Clinic Staff"
                                dropdownExpanded = false
                            }
                    )

                }


                Spacer(
                        modifier = Modifier.height(20.dp)
                )


                Button(
                        onClick = {
                            // Registration API later
                        },

                        modifier = Modifier.fillMaxWidth(),

                        colors = ButtonDefaults.buttonColors(
                                containerColor =
                                MaterialTheme.colorScheme.primary
                        )
                ) {

                    Text("Register")
                }


                Spacer(
                        modifier = Modifier.height(12.dp)
                )


                OutlinedButton(
                        onClick = onLoginClick,

                        modifier = Modifier.fillMaxWidth()
                ) {

                    Text("Already have an account? Login")
                }

            }
        }
    }
}