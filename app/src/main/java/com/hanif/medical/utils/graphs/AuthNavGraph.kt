package com.hanif.medical.utils.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hanif.medical.Screens.LoginScreen
import com.hanif.medical.Screens.RegisterScreen
import com.hanif.medical.Screens.WelcomeScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavGraph(navController: NavHostController, prefKeys: Int) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = if (prefKeys != 0) AuthScreen.Login.route else AuthScreen.OnBoarding.route
    ) {

        composable(route = AuthScreen.OnBoarding.route) {
            WelcomeScreen(onNavigate = {navController.navigate(it.route)},navController = navController)
        }


        composable(route = AuthScreen.Login.route) {
            LoginScreen(onNavigate = {navController.navigate(it.route)},navController = navController)
        }

        composable(route = AuthScreen.SignUp.route) {
            RegisterScreen(onNavigate = {navController.navigate(it.route)},navController = navController, onPopBackStack = {navController.popBackStack()})
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object OnBoarding : AuthScreen(route = "ON_BOARDING")
}