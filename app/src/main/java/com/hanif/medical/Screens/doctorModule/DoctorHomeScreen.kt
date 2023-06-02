package com.hanif.medical.Screens.doctorModule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanif.medical.Screens.doctorModule.auth.DoctorLoginViewModel

@Composable
fun DoctorHomeScreen(viewModel: DoctorLoginViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = true) {
        viewModel.getDoctorAppointmentDataList()
    }
}