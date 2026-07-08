package edu.cit.gaane.caresync.mobile.shared.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.cit.gaane.caresync.mobile.features.authentication.screens.LoginScreen
import edu.cit.gaane.caresync.mobile.features.authentication.screens.RegisterScreen

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
    }
}