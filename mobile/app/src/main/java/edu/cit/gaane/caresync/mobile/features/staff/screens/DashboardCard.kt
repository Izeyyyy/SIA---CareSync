package edu.cit.gaane.caresync.mobile.features.staff.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardCard(

    title: String,

    value: String

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Text(title)

            Spacer(Modifier.height(8.dp))

            Text(

                value,

                style = MaterialTheme.typography.headlineMedium

            )

        }

    }

}