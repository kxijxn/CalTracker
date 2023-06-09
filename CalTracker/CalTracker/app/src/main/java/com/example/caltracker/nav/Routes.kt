package com.example.caltracker.nav


sealed class Routes(val route: String) {

    object Login : Routes("Login")
    object UserRegistration : Routes("UserRegistration")
    object HomePage : Routes("HomePage")
    object Profile : Routes("Profile")
    object Service : Routes("Service")
    object Notice : Routes("Notice")
    object Mechanic: Routes("Mechanic")
    object Payment: Routes("Payment")
}