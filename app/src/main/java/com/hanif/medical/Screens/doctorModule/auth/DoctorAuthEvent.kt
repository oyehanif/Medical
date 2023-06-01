package com.hanif.medical.Screens.doctorModule.auth


sealed class DoctorAuthEvent {

    data class OnEmailChange(val email: String) : DoctorAuthEvent()

    data class OnPasswordChange(val password: String) : DoctorAuthEvent()

    data class OnCheckChange(val isCheck: Boolean) : DoctorAuthEvent()

    object OnSignInClick : DoctorAuthEvent()

    data class OnSubmitClick(val email: String? = null) : DoctorAuthEvent()

    data class OnPopBack(val route: String? = null) : DoctorAuthEvent()
}