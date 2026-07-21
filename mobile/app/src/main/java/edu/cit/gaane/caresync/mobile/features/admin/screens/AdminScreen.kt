package edu.cit.gaane.caresync.mobile.features.admin.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AdminScreen(
    firstName: String
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Welcome, $firstName",
            style = MaterialTheme.typography.headlineMedium
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Text(
            text = "Admin Dashboard",
            style = MaterialTheme.typography.titleLarge
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Button(
            onClick = {

            }
        ) {

            Text("Manage Users")

        }

    }

}