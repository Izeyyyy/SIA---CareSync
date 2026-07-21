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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import edu.cit.gaane.caresync.mobile.features.authentication.viewmodel.LoginViewModel
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LoginScreen(
        onRegisterClick: () -> Unit,
        onLoginSuccess: (String) -> Unit,
        loginViewModel: LoginViewModel = viewModel()


){

    fun isValidEmail(email: String): Boolean {

        return android.util.Patterns.EMAIL_ADDRESS
            .matcher(email)
            .matches()

    }


    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }

    val loginResult =
            loginViewModel.loginResult.collectAsState()

    val loggedInUser =
            loginViewModel.loggedInUser.collectAsState()

    val isLoading =
        loginViewModel.isLoading.collectAsState()


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

                if (message.isNotEmpty()) {

                    Text(
                            text = message,
                            color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(
                            modifier = Modifier.height(10.dp)
                    )
                }

                LaunchedEffect(loggedInUser.value) {

                    loggedInUser.value?.let { user ->

                        onLoginSuccess(user.role)

                    }

                }

                LaunchedEffect(loginResult.value) {

                    loginResult.value?.let {

                        if (it != "Login successful") {

                            message = it

                        }

                    }

                }

                Button(
                    enabled = !isLoading.value,

                    onClick = {

                        println("Login button clicked")


                        if(email.isBlank()) {

                            message = "Email is required"
                            return@Button

                        }


                        if(password.isBlank()) {

                            message = "Password is required"
                            return@Button

                        }

                        if(!isValidEmail(email)) {

                            message = "Enter a valid email address"
                            return@Button

                        }


                        loginViewModel.login(
                            email,
                            password
                        )

                    },

                    modifier = Modifier.fillMaxWidth(),

                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            MaterialTheme.colorScheme.primary
                    )
                ) {

                    Text(
                        if(isLoading.value)
                            "Logging in..."
                        else
                            "Login"
                    )

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