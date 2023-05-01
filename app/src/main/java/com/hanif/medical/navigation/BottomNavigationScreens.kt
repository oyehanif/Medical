package com.hanif.medical.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import com.hanif.medical.R

sealed class BottomNavigationScreens(val route: String, val resourceId: String, val icon: ImageVector) {
    object HomeBot : BottomNavigationScreens("HomeBot", "Home", Icons.Filled.Home)
    object Schedule : BottomNavigationScreens("Schedule", "Schedule", Icons.Filled.DateRange)
        object Report : BottomNavigationScreens("Report", "Report", Icons.Filled.Build)
    object Notifications : BottomNavigationScreens("Notifications", "Notifications", Icons.Filled.Notifications)
}