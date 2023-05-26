package com.hanif.medical.utils.graphs

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.*
import com.hanif.medical.Screens.AllDoctorScreen
import com.hanif.medical.Screens.AppointmentScheduleScreen
import com.hanif.medical.Screens.CardPaymentScreen
import com.hanif.medical.Screens.DetailDoctorScreen
import com.hanif.medical.Screens.DetailShoppingScreen
import com.hanif.medical.Screens.HomeScreen
import com.hanif.medical.Screens.OrderSuccessfulScreen
import com.hanif.medical.Screens.ReportScreen
import com.hanif.medical.Screens.ShoppingAddressScreen
import com.hanif.medical.Screens.ShoppingPrePaymentScreen
import com.hanif.medical.Screens.ShoppingScreen
import com.hanif.medical.Screens.doctor.DoctorBookingProcessFirstScreens
import com.hanif.medical.Screens.doctor.DoctorBookingProcessSecondScreen
import com.hanif.medical.Screens.doctor.DoctorSharedViewModel
import com.hanif.medical.Screens.shopping.ShoppingSharedViewModel
import com.hanif.medical.utils.Routes
import com.matrixhive.subsalert.component.notification.NotificationScreen
import com.matrixhive.subsalert.component.setting.SettingScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {

    val shoppingSharedViewModel: ShoppingSharedViewModel = hiltViewModel()
    val doctorSharedViewModel: DoctorSharedViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController, sharedViewModel = doctorSharedViewModel
            )
        }

        composable(
            route = Routes.ROUTE_PRE_ADD_MANUAL_SCREEN,
        ) {
            /*PreAddManualScreen(onNavigate = { event -> navController.navigate(event.route) },
                navController = navController)*/
        }

        composable(
            route = Routes.REPORT_SCREEN,
        ) {
            ReportScreen()
        }
        composable(
            route = Routes.DETAIL_DOCTOR_SCREEN,
        ) {
            DetailDoctorScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController,
                sharedViewModel = doctorSharedViewModel
            )
        }

        composable(
            route = Routes.SHOPPING_SCREEN,
        ) {
            ShoppingScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController,
                sharedViewModel = shoppingSharedViewModel
            )
        }
        composable(
            route = Routes.SHOPPING_SUCCESSFUL_SCREEN,
        ) {
            OrderSuccessfulScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController
            )
        }
        composable(
            route = Routes.SHOPPING_CARD_PAYMENT_SCREEN,
        ) {
            CardPaymentScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController
            )
        }

        composable(
            route = Routes.SHOPPING_ADDRESS_SCREEN,
        ) {
            ShoppingAddressScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController
            )
        }
        composable(
            route = Routes.SHOPPING_PRE_PAYMENT_SCREEN,
        ) {
            ShoppingPrePaymentScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController
            )
        }

        composable(
            route = Routes.SHOPPING_DETAIL_SCREEN,
        ) {
            DetailShoppingScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController, sharedViewModel = shoppingSharedViewModel
            )
        }

        composable(
            route = Routes.ALL_DOCTOR_SCREEN,
        ) {
            AllDoctorScreen(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController
            )
        }
        composable(
            route = Routes.DOCTOR_BOOKING_PROCESS_FIRST_SCREEN,
        ) {
            DoctorBookingProcessFirstScreens(
                onNavigate = { event -> navController.navigate(event.route) },
                navController = navController
            )
        }

        composable(
            route = Routes.DOCTOR_BOOKING_PROCESS_SECOND_SCREEN,
        ) {
            DoctorBookingProcessSecondScreen(
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
        composable(route = BottomBarScreen.Appointment_Schedule.route) {
            AppointmentScheduleScreen()
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

        /*composable(route =Routes.EDIT_PROFILE_SCREEN ) {
            EditProfileScreen()
        }*/

    }
}