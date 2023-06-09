package com.example.caltracker.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.caltracker.nav.Routes
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ScreenMain(auth: FirebaseAuth){

    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Routes.Login.route) {


        composable(Routes.Login.route) {
            Login(navController = navController, auth = auth)
        }

        composable(Routes.UserRegistration.route) {
            UserRegistration(navController = navController, auth = auth)
        }

        composable(Routes.HomePage.route) {
            HomePage(navController = navController)
        }

        composable(Routes.Profile.route) {
            Profile(navController = navController)
        }

        composable(Routes.Service.route) {
            Service(navController = navController, auth = auth)
        }

        composable(Routes.Notice.route) {
            Notice(navController = navController, auth = auth)
        }

        composable(Routes.Mechanic.route) {
            Mechanic(navController = navController)
        }

        composable(Routes.Payment.route) {
            Payment(navController = navController)
        }


    }

}
