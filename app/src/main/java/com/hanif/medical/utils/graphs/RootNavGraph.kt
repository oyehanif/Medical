package com.hanif.medical.utils.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RootNavigationGraph(navController: NavHostController, prefKeysForIsLogin: Int , prefKeysForIsOnBoarding: Int) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = if (prefKeysForIsLogin != 0) Graph.HOME else Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController,prefKeysForIsOnBoarding)
        composable(route = Graph.HOME) {
            HomeNav()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val AUTHENTICATION = "auth_graph"
}