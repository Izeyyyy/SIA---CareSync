package edu.cit.gaane.caresync.mobile.shared.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.cit.gaane.caresync.mobile.features.authentication.screens.LoginScreen
import edu.cit.gaane.caresync.mobile.features.authentication.screens.RegisterScreen
import edu.cit.gaane.caresync.mobile.features.doctor.screens.DoctorDashboardScreen
import edu.cit.gaane.caresync.mobile.features.staff.screens.StaffScreen
import edu.cit.gaane.caresync.mobile.features.admin.screens.AdminScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

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
                    firstName = "Doctor"
            )

        }


        composable("staff_dashboard") {

            StaffScreen(
                    firstName = "Staff"
            )

        }

        composable("admin_dashboard") {

            AdminScreen(
                firstName = "Admin"
            )

        }
    }
}