package com.hanif.medical.utils.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import com.hanif.medical.Screens.AllDoctorScreen
import com.hanif.medical.Screens.HomeScreen
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.graphs.BottomBarScreen
import com.matrixhive.subsalert.component.notification.NotificationScreen
import com.matrixhive.subsalert.component.setting.EditProfileScreen
import com.matrixhive.subsalert.component.setting.SettingScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController
            )
        }

        composable(
            route = Routes.ROUTE_PRE_ADD_MANUAL_SCREEN,
        ) {
            /*PreAddManualScreen(onNavigate = { event -> navController.navigate(event.route) },
                navController = navController)*/
        }

        composable(
            route = Routes.ROUTE_ADD_EDIT_SCREEN + "?Id={Id}",
            arguments = listOf(
                navArgument(name = "Id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            /*AddManualScreen(onPopBackStack = {
                navController.popBackStack()
            })*/
        }
        composable(route = BottomBarScreen.Analysis.route) {
            AllDoctorScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController
            )
        }

        composable(route = BottomBarScreen.Notifications.route) {
            NotificationScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController
            )
        }

        composable(route = BottomBarScreen.Settings.route) {
            SettingScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController
            )
        }

        composable(route =Routes.EDIT_PROFILE_SCREEN ) {
            EditProfileScreen()
        }

    }
}