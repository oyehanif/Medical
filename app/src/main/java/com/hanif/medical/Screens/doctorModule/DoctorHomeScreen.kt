package com.hanif.medical.Screens.doctorModule

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanif.medical.Screens.ItemScheduleAppointment
import com.hanif.medical.Screens.doctor.BookingProcess
import com.hanif.medical.Screens.doctorModule.auth.DoctorLoginViewModel
import com.hanif.medical.repository.ReportModel
import com.hanif.medical.utils.doctorId
import com.hanif.medical.utils.graphs.UIEvent
import com.matrixhive.subsalert.component.notification.EmptyScreen

@Composable
fun DoctorHomeScreen(viewModel: DoctorLoginViewModel = hiltViewModel()) {

    val scaffoldState = rememberScaffoldState()
    val viewModel1: DoctorHomeViewModel = hiltViewModel()
    LaunchedEffect(key1 = true) {


        viewModel.uiEvent.collect { event ->
            when (event) {


                is UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message, actionLabel = event.action
                    )
                }

                else -> Unit
            }
        }
    }


    val state = viewModel.doctorAppointmentstate
    LaunchedEffect(key1 = true) {
        viewModel.getDoctorAppointmentDataList(doctorId)
    }
    var text by rememberSaveable { mutableStateOf("") }

    androidx.compose.material.Scaffold() {
        val innerPadding = it
        if (state.companies.isNotEmpty()) {
            LazyColumn() {
                items(state.companies.size) { i ->
                    val bookingProcessModel: BookingProcess = state.companies[i]
                    ItemScheduleAppointment(
                        bookingProcessModel,
                        isDoctor = true,
                        text = text,
                        onValueChange = { text = it }) {
                        viewModel1.insertReports(ReportModel(text))
                        text = ""
                    }
                }
            }
        } else {
            EmptyScreen("Better Luck!", "There is not appointment schedule stay tune!")
        }
    }
}