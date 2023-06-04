package com.hanif.medical

import android.os.Build
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.hanif.medical.Screens.auth.AuthEvent
import com.hanif.medical.Screens.validation.ValidateEmail
import com.hanif.medical.Screens.validation.ValidatePassword
import com.hanif.medical.datastore.DataStore
import com.hanif.medical.models.Resource
import com.hanif.medical.repository.AuthRepository
import com.hanif.medical.repository.LoginRequest
import com.hanif.medical.utils.graphs.AuthScreen
import com.hanif.medical.utils.graphs.Graph
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val prefImpl: DataStore
) : BaseViewModal() {

    var state by mutableStateOf(ScreenState(null))

    var emailState by mutableStateOf(ValidationScreenState())

    var passwordState by mutableStateOf(ValidationScreenState())

    var isRememberValid by mutableStateOf(false)
        private set

    var device_id by mutableStateOf("")
        private set

    fun updateDeviceId(deviceId: String) {
        device_id = deviceId
    }

    init {
        viewModelScope.launch {
            /*prefImpl.getIsRemember().collect {
                if (!it?.email.isNullOrEmpty()) {
                    emailState = emailState.copy(text = it!!.email)
                    passwordState = passwordState.copy(text = it.password)
                    isRememberValid = it.isCheck
                }
            }*/
        }
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.OnEmailChange -> {
                val result = ValidateEmail().execute(emailState.text)
                emailState = emailState.copy(
                    text = event.email.trim(),
                    isError = !result.successful,
                    errorMes = if (!result.successful) result.errorMessage!! else ""

                )
                // emailNameValid = !result.successful

            }

            is AuthEvent.OnPasswordChange -> {
                val result = ValidatePassword().execute(passwordState.text, true)
                passwordState = passwordState.copy(
                    text = event.password.trim(),
                    isError = !result.successful,
                    errorMes = if (!result.successful) result.errorMessage!! else ""
                )
            }

            is AuthEvent.OnCheckChange -> {
                isRememberValid = event.isCheck
            }

            AuthEvent.OnSignUpClick -> {
                sendUiEvent(UIEvent.Navigate(AuthScreen.SignUp.route))
            }

            is AuthEvent.OnSubmitClick -> {
                viewModelScope.launch {

                    val vEmail = ValidateEmail().execute(emailState.text)
                    val vPassword = ValidatePassword().execute(passwordState.text, false)
                    val hasError = listOf(
                        vEmail, vPassword
                    ).any { !it.successful }

                    if (hasError) {
                        emailState = emailState.copy(
                            errorMes = vEmail.errorMessage ?: "",
                            isError = !vEmail.successful
                        )
                        passwordState = passwordState.copy(
                            errorMes = vPassword.errorMessage ?: "",
                            isError = !vPassword.successful
                        )
                        return@launch
                    }
                    getLoginResponse(
                        LoginRequest(
                            emailState.text, passwordState.text, isRememberValid
                        )
                    )
                }
            }

            is AuthEvent.OnPopBack -> {
                sendUiEvent(UIEvent.PopBackStack)
            }

            is AuthEvent.OnForgetPasswordClick -> {
                sendUiEvent(UIEvent.Navigate(AuthScreen.ForgetPassword.route))
            }

            else -> Unit
        }
    }


    private fun getLoginResponse(loginRequest: LoginRequest) {
        viewModelScope.launch {
            repository
                .login(loginRequest)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            sendUiEvent(UIEvent.Navigate(Graph.HOME))
                            state = state.copy(isLoading = false)

                        }

                        is Resource.Error -> {
                            sendUiEvent(UIEvent.ShowSnackbar("${result.error}"))
                            state = state.copy(isLoading = false)
                        }

                        is Resource.Loading -> {
                            state = state.copy(isLoading = true)
                        }
                    }
                }
        }
    }

}

data class ValidationScreenState(
    var text: String = "",
    val isError: Boolean = false,
    val errorMes: String = "",
)

data class ScreenState<T>(
    var data: T? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)