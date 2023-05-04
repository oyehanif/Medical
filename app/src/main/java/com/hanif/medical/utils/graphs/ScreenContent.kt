package com.hanif.medical.utils.graphs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.hanif.medical.R

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

    object Analysis : BottomBarScreen(
        route = "ANALYSIS",
        title = "ANALYSIS",
        icon = Icons.Default.Home,
        selectedIcon = Icons.Default.Home
    )

    object Notifications : BottomBarScreen(
        route = "NOTIFICATION",
        title = "NOTIFICATION",
        icon = Icons.Default.Home,
        selectedIcon = Icons.Default.Home
    )

    object Settings : BottomBarScreen(
        route = "SETTINGS",
        title = "SETTINGS",
        icon = Icons.Default.Home,
        selectedIcon = Icons.Default.Home
    )
}
