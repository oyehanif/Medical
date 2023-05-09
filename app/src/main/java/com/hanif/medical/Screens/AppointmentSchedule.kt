package com.hanif.medical.Screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.matrixhive.subsalert.component.notification.EmptyScreen

@Preview
@Composable
fun AppointmentScheduleScreen() {
    EmptyScreen("No Appointment Book In Past","appointment schedule not found please book appointment to see history")
}