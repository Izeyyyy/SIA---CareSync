package edu.cit.gaane.caresync.mobile.shared.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import edu.cit.gaane.caresync.mobile.features.authentication.screens.LoginScreen
import edu.cit.gaane.caresync.mobile.features.authentication.screens.RegisterScreen
import edu.cit.gaane.caresync.mobile.features.doctor.screens.DoctorDashboardScreen
import edu.cit.gaane.caresync.mobile.features.staff.screens.StaffScreen
import edu.cit.gaane.caresync.mobile.features.admin.screens.AdminScreen
import edu.cit.gaane.caresync.mobile.features.patients.screens.PatientListScreen
import edu.cit.gaane.caresync.mobile.features.patients.screens.PatientDetailsScreen
import edu.cit.gaane.caresync.mobile.features.patients.screens.RegisterPatientScreen
import edu.cit.gaane.caresync.mobile.features.consultation.screens.RegisterConsultationScreen
import edu.cit.gaane.caresync.mobile.features.consultation.screens.PatientConsultationListScreen
import edu.cit.gaane.caresync.mobile.features.consultation.screens.ConsultationDetailsScreen
import edu.cit.gaane.caresync.mobile.shared.storage.SessionProvider
import edu.cit.gaane.caresync.mobile.features.patients.screens.EditPatientScreen
import edu.cit.gaane.caresync.mobile.features.consultation.screens.EditConsultationScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    val logout = {
        scope.launch {
            SessionProvider.sessionManager.clearSession()
            navController.navigate("login") {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    NavHost(
            navController = navController,
            startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(
                    onRegisterClick = {
                        navController.navigate("register")
                    },

                    onLoginSuccess = { role ->

                        when(role) {
                            "Admin" -> {
                                navController.navigate("admin_dashboard")
                            }

                            "Doctor" -> {
                                navController.navigate("doctor_dashboard")
                            }

                            "Clinic Staff" -> {
                                navController.navigate("staff_dashboard")
                            }

                        }

                    }
            )
        }

        composable("register") {
            RegisterScreen(
                    onLoginClick = {
                        navController.navigate("login")
                    }
            )
        }

        composable("doctor_dashboard") {

            DoctorDashboardScreen(
                firstName = "Doctor",
                onPatientsClick = {
                    navController.navigate("doctor_patients")
                },
                onLogout = { logout() }
            )

        }


        composable("staff_dashboard") {

            StaffScreen(
                firstName = "Staff",
                onPatientsClick = {
                    navController.navigate("patients")
                },
                onRegisterPatientClick = {
                    navController.navigate("register_patient")
                },
                onLogout = { logout() }
            )

        }

        composable("admin_dashboard") {

            AdminScreen(
                firstName = "Admin"
            )

        }

        composable("patients") {

            PatientListScreen(
                onPatientClick = { id ->
                    navController.navigate("patient/$id")
                },
                onAddPatientClick = {
                    navController.navigate("register_patient")
                },
                onBackClick = { navController.popBackStack() },
                showRegisterButton = true
            )
        }

        composable("doctor_patients") {

            PatientListScreen(
                onPatientClick = { id ->
                    navController.navigate("doctor_patient/$id")
                },
                onAddPatientClick = {},
                onBackClick = { navController.popBackStack() },
                showRegisterButton = false
            )

        }

        composable(

            route = "patient/{id}",

            arguments = listOf(

                navArgument("id") {

                    type = NavType.LongType

                }

            )

        ) {


            val id =

                it.arguments?.getLong("id") ?: 0L

            PatientDetailsScreen(

                patientId = id,

                onConsultations = {
                    navController.navigate("patient/$id/consultations")
                },

                onNewConsultation = {
                    navController.navigate("patient/$id/newConsultation")
                },

                onEditPatient = {

                    navController.navigate(
                        "patient/$id/edit"
                    )

                },

                onBackClick = { navController.popBackStack() }
            )

        }

        composable(
            route = "doctor_patient/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )
        ) {

            val id = it.arguments?.getLong("id") ?: 0L

            PatientDetailsScreen(
                patientId = id,
                onConsultations = {
                    navController.navigate("patient/$id/consultations")
                },
                onNewConsultation = {
                    navController.navigate("patient/$id/newConsultation")
                },

                onBackClick = { navController.popBackStack() }
            )

        }

        composable(

            route = "patient/{id}/consultations",

            arguments = listOf(

                navArgument("id") {

                    type = NavType.LongType

                }

            )

        ) {

            val patientId = it.arguments?.getLong("id") ?: 0L

            PatientConsultationListScreen(
                patientId = patientId,
                onNewConsultation = {
                    navController.navigate("patient/$patientId/newConsultation")
                },
                onConsultationClick = { consultationId ->
                    navController.navigate("patient/$patientId/consultation/$consultationId")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(

            route = "patient/{id}/newConsultation",

            arguments = listOf(

                navArgument("id") {

                    type = NavType.LongType

                }

            )

        ) {

            val patientId = it.arguments?.getLong("id") ?: 0L

            RegisterConsultationScreen(
                patientId = patientId,
                onFinished = {
                    navController.popBackStack()
                },
                onBackClick = { navController.popBackStack() }
            )

        }

        composable(

            route = "patient/{patientId}/consultation/{consultationId}",

            arguments = listOf(

                navArgument("patientId") {

                    type = NavType.LongType

                },

                navArgument("consultationId") {

                    type = NavType.LongType

                }

            )

        ) {

            val patientId =
                it.arguments?.getLong("patientId") ?: 0L

            val consultationId =
                it.arguments?.getLong("consultationId") ?: 0L


            ConsultationDetailsScreen(

                consultationId = consultationId,

                onEditClick = {

                    navController.navigate(
                        "patient/$patientId/consultation/$consultationId/edit"
                    )

                },

                onBackClick = {
                    navController.popBackStack()
                }

            )

        }

        composable(

            route = "patient/{patientId}/consultation/{consultationId}/edit",

            arguments = listOf(

                navArgument("patientId"){
                    type = NavType.LongType
                },

                navArgument("consultationId"){
                    type = NavType.LongType
                }

            )

        ){

            val patientId =
                it.arguments?.getLong("patientId") ?: 0L


            val consultationId =
                it.arguments?.getLong("consultationId") ?: 0L



            EditConsultationScreen(

                consultationId = consultationId,

                patientId = patientId,

                onFinished = {

                    navController.popBackStack()

                },

                onBackClick = {

                    navController.popBackStack()

                }

            )

        }

        composable(
            route = "patient/{id}/edit",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )
        ) {

            val patientId =
                it.arguments?.getLong("id") ?: 0L

            EditPatientScreen(

                patientId = patientId,

                onFinished = {

                    navController.popBackStack()

                },

                onBackClick = {

                    navController.popBackStack()

                }

            )

        }


        composable("register_patient") {

            RegisterPatientScreen(
                onFinished = {
                    navController.popBackStack()
                },
                onBackClick = { navController.popBackStack() }
            )

        }
    }
}