package edu.cit.gaane.caresync.mobile.features.staff.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun StaffScreen(
        firstName: String?
) {

    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {

        Text(
                text = "CareSync",
                style = MaterialTheme.typography.headlineSmall
        )


        Spacer(
                modifier = Modifier.height(20.dp)
        )


        Text(
                text = "Clinic Staff Dashboard",
                style = MaterialTheme.typography.titleLarge
        )


        Spacer(
                modifier = Modifier.height(12.dp)
        )


        Text(
                text = "Welcome ${firstName ?: "there"}"
        )


        Text(
                text = "Role: Clinic Staff"
        )

    }
}