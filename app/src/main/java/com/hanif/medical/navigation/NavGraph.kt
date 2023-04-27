package com.hanif.medical.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.hanif.medical.Screens.ForgetPasswordScreen
import com.hanif.medical.Screens.HomeScreen
import com.hanif.medical.Screens.LoginScreen
import com.hanif.medical.Screens.RegisterScreen
import com.hanif.medical.Screens.WelcomeScreen


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen()
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController)
        }

        composable(route = Screen.ForgetPassword.route) {
            ForgetPasswordScreen(navController = navController)
        }
    }
}