package com.hanif.medical

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.hanif.medical.Screens.PhoneValuesForValidation
import com.hanif.medical.Screens.auth.AuthEvent
import com.hanif.medical.Screens.validation.ValidateCheckbox
import com.hanif.medical.Screens.validation.ValidateContact
import com.hanif.medical.Screens.validation.ValidateContact1
import com.hanif.medical.Screens.validation.ValidateEmail
import com.hanif.medical.Screens.validation.ValidateName
import com.hanif.medical.Screens.validation.ValidatePassword
import com.hanif.medical.models.Resource
import com.hanif.medical.repository.AuthRepository
import com.hanif.medical.repository.HomeRepository
import com.hanif.medical.utils.graphs.AuthScreen
import com.hanif.medical.utils.graphs.Graph
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authUseCase: AuthRepository) :
    BaseViewModal() {

    var state by mutableStateOf(ScreenState(null))

    var name by mutableStateOf("")
        private set

    var nameValid by mutableStateOf(false)
        private set

    var nameErrMsg: String by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var emailNameValid by mutableStateOf(false)
        private set

    var emailErrMsg by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    var phoneNumberValid by mutableStateOf(true)
        private set

    var fieldResult by mutableStateOf(PhoneValuesForValidation("", ""))

    var password by mutableStateOf("")
        private set

    var passwordValid by mutableStateOf(false)
        private set

    var passwordErrMsg by mutableStateOf("")
        private set

    var isCheckedTC by mutableStateOf(false)
        private set

    var isCheckedValid by mutableStateOf(false)
        private set

    var checkboxError by mutableStateOf("")
        private set

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.OnEmailChange -> {
                email = event.email.trim()
                val result = ValidateEmail().execute(email)
                emailNameValid = !result.successful
                if (!result.successful) {
                    emailErrMsg = result.errorMessage!!
                }
            }

            is AuthEvent.OnNameChange -> {
                name = event.name
                val result = ValidateName().execute(name, errorName = "name")
                nameValid = !result.successful
                if (!result.successful) {
                    nameErrMsg = result.errorMessage!!
                }
            }

            is AuthEvent.OnPhoneNumberChange -> {
                phone = event.phone_number
                val result = ValidateContact1().execute(contact = phone, fieldResult)
                phoneNumberValid = result.successful
            }


            is AuthEvent.OnPasswordChange -> {
                password = event.password
                val result = ValidatePassword().execute(password, true)
                passwordValid = !result.successful
                if (!result.successful) {
                    passwordErrMsg = result.errorMessage!!
                }
            }

            is AuthEvent.OnCheckChange -> {
                isCheckedTC = event.isCheck
                val result = ValidateCheckbox().execute(isCheckedTC)
                isCheckedValid = !result.successful
                if (!result.successful) {
                    checkboxError = result.errorMessage!!
                }
            }

            AuthEvent.OnSignInClick -> {
                sendUiEvent(UIEvent.PopBackStack)
            }

            AuthEvent.OnPopBack() -> {
                sendUiEvent(UIEvent.PopBackStack)
            }

            is AuthEvent.OnSubmitClick -> {
                viewModelScope.launch {

                    val vEmail = ValidateEmail().execute(email)
                    val vName = ValidateName().execute(name, errorName = "name")
                    val vPassword = ValidatePassword().execute(password, true)
                    val vClick = ValidateCheckbox().execute(isCheckedTC)
                    val vPhone = ValidateContact1().execute(contact = phone, fieldResult)


                    val hasError =
                        listOf(vEmail, vName, vPassword, vClick).any { !it.successful }

                    if (hasError) {
                        passwordValid = !vPassword.successful
                        emailNameValid = !vEmail.successful
                        nameValid = !vName.successful
                        passwordValid = !vPassword.successful
                        isCheckedValid = !vClick.successful
                        nameErrMsg = vName.errorMessage ?: ""
                        passwordErrMsg = vPassword.errorMessage ?: ""
                        checkboxError = vClick.errorMessage ?: ""
                        emailErrMsg = vEmail.errorMessage ?: ""
                        phoneNumberValid = vPhone.successful
                        return@launch
                    }
                    apiResponse()
                }
            }

            else -> Unit
        }
    }

    private suspend fun apiResponse() {
        authUseCase.register(name, email, phone, password).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }

                is Resource.Success -> {
                    sendUiEvent(UIEvent.Navigate(Graph.HOME))
                    state = state.copy(isLoading = false)
                }

                is Resource.Error -> {
                    sendUiEvent(UIEvent.ShowSnackbar(result.error.toString()))
                    state = state.copy(isLoading = false)
                }
            }
        }
    }
}