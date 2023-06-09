package com.hanif.medical.utils.graphs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ScreenContent(name: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable { onClick() },
            text = name,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "HOME",
        title = "HOME",
        icon = Icons.Default.Home,
        selectedIcon = Icons.Default.Home
    )

    object Appointment_Schedule : BottomBarScreen(
        route = "APPOINTMENT",
        title = "APPOINTMENT",
        icon = Icons.Default.DateRange,
        selectedIcon = Icons.Default.DateRange
    )

    object Notifications : BottomBarScreen(
        route = "NOTIFICATION",
        title = "NOTIFICATION",
        icon = Icons.Default.Notifications,
        selectedIcon = Icons.Default.Notifications
    )

    object Settings : BottomBarScreen(
        route = "PROFILE",
        title = "PROFILE",
        icon = Icons.Default.Person,
        selectedIcon = Icons.Default.Person
    )
}
