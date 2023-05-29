package com.hanif.medical.Screens.doctor

sealed class DoctorBookingEvent {


    data class OnGenderChange(val gender: String) : DoctorBookingEvent()

    data class OnWeightChange(val weight: String) : DoctorBookingEvent()

    data class OnHeightChange(val height: String) : DoctorBookingEvent()

    data class OnAgeChange(val age: String) : DoctorBookingEvent()

    object OnSubmitClick : DoctorBookingEvent()

    object OnPopBack : DoctorBookingEvent()

}