package edu.cit.gaane.caresync.mobile.features.authentication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme

@Composable
fun LoginScreen(
        onRegisterClick: () -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }


    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .background(
                            MaterialTheme.colorScheme.background
                    )
                    .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {


        // Header

        Text(
                text = "CareSync",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
        )


        Spacer(
                modifier = Modifier.height(6.dp)
        )


        Text(
                text = "Patient Record Management System",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
        )


        Spacer(
                modifier = Modifier.height(20.dp)
        )


        // Card

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
                        text = "Login",
                        style = MaterialTheme.typography.titleMedium
                )


                Spacer(
                        modifier = Modifier.height(20.dp)
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
                        modifier = Modifier.height(14.dp)
                )


                OutlinedTextField(
                        value = password,

                        onValueChange = {
                            password = it
                        },

                        label = {
                            Text("Password")
                        },

                        visualTransformation =
                        PasswordVisualTransformation(),

                        modifier = Modifier.fillMaxWidth()
                )


                Spacer(
                        modifier = Modifier.height(20.dp)
                )


                Button(
                        onClick = {
                            // API later
                        },

                        modifier = Modifier.fillMaxWidth(),

                        colors = ButtonDefaults.buttonColors(
                                containerColor =
                                MaterialTheme.colorScheme.primary
                        )
                ) {

                    Text("Login")
                }


                Spacer(
                        modifier = Modifier.height(12.dp)
                )


                OutlinedButton(
                        onClick = onRegisterClick,

                        modifier = Modifier.fillMaxWidth()
                ) {

                    Text("Register")
                }

            }
        }
    }
}