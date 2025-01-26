package com.example.mymainapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavRoutes(val route: String ){
    object WelcomeScreen : NavRoutes("welcome")
    object HomeScreen : NavRoutes("home")
    object LessonsScreen : NavRoutes("lessons")
    object SettingsScreen : NavRoutes("settings")

    data class BarItem(
        val title: String,
        val image: ImageVector,
        val route: String
    )

    object NavBarItems {
        val BarItems = listOf(
            BarItem(
                title = "Home",
                image = Icons.Default.Home,
                route = "home"
            ),
            BarItem(
                title = "Lessons",
                image = Icons.Default.Menu,
                route = "lessons"
            ),
            BarItem(
                title = "Settings",
                image = Icons.Default.Settings,
                route = "settings"
            )
        )
    }
}