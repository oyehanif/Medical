package com.hanif.medical.Screens.auth

sealed class AuthEvent {
    data class OnNameChange(val name: String) : AuthEvent()

    data class OnEmailChange(val email: String) : AuthEvent()

    data class OnPhoneNumberChange(val phone_number: String) : AuthEvent()

    data class OnPasswordChange(val password: String) : AuthEvent()

    data class OnCheckChange(val isCheck: Boolean) : AuthEvent()

    object OnSignInClick : AuthEvent()

    object OnSignUpClick : AuthEvent()

    object OnForgetPasswordClick : AuthEvent()

    data class OnSubmitClick(val email: String? = null) : AuthEvent()

    data class OnPopBack(val route: String? = null) : AuthEvent()

}