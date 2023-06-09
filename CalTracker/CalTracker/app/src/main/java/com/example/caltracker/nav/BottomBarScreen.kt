package com.example.caltracker.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route:String,
    val title: String,
    val icon: ImageVector

) {
    object Home: BottomBarScreen("HomePage", "Home", Icons.Default.Home)
    object Profile: BottomBarScreen("Profile", "Profile", Icons.Default.PermIdentity)
    object Service: BottomBarScreen("Service", "Calories", Icons.Default.RunCircle)
    object Notice: BottomBarScreen("Notice", "History", Icons.Default.History)
}


