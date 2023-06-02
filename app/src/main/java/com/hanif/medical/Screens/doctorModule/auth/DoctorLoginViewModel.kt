package com.hanif.medical.Screens.doctorModule.auth

import android.os.Build
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.hanif.medical.Screens.shopping.ShoppingProcessModel
import com.hanif.medical.Screens.validation.ValidateEmail
import com.hanif.medical.Screens.validation.ValidatePassword
import com.hanif.medical.datastore.DataStore
import com.hanif.medical.models.Resource
import com.hanif.medical.repository.DoctorAuthRepository
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorLoginViewModel @Inject constructor(
    private val repository: DoctorAuthRepository
) : BaseViewModal() {  //    private val prefImpl:DataStore

    var state by mutableStateOf(DoctorLoginState(""))

    var userEmail by mutableStateOf("")
        private set

    var emailNameValid by mutableStateOf(false)
        private set

    var emailErrMsg by mutableStateOf("")
        private set


    var userPassword by mutableStateOf("")
        private set

    var passwordValid by mutableStateOf(false)
        private set

    var passwordErrMsg by mutableStateOf("")
        private set


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
                    userEmail = it!!.email
                    userPassword = it.password
                    isRememberValid = it.isCheck
                }
            }*/
        }
    }

    fun onEvent(event: DoctorAuthEvent) {
        when (event) {
            is DoctorAuthEvent.OnEmailChange -> {
                userEmail = event.email.trim()
                /*val result = ValidateEmail().execute(userEmail)
                emailNameValid = !result.successful
                if (!result.successful) {
                    emailErrMsg = result.errorMessage!!
                }*/
            }

            is DoctorAuthEvent.OnPasswordChange -> {
                userPassword = event.password
                val result = ValidatePassword().execute(userPassword, true)
                passwordValid = !result.successful
                if (!result.successful) {
                    passwordErrMsg = result.errorMessage!!
                }
            }

            is DoctorAuthEvent.OnCheckChange -> {
                isRememberValid = event.isCheck
            }


            is DoctorAuthEvent.OnSubmitClick -> {
                viewModelScope.launch {

                    //val vEmail = ValidateEmail().execute(userEmail)
                    val vPassword = ValidatePassword().execute(userPassword, false)
                    val hasError = listOf(
                        /*vEmail,*/ vPassword
                    ).any { !it.successful }

                    if (hasError) {
                        passwordValid = !vPassword.successful
                        passwordErrMsg = vPassword.errorMessage ?: ""
//                        emailErrMsg = vEmail.errorMessage ?: ""
//                        emailNameValid = !vEmail.successful
                        return@launch
                    }
                    getLoginResponse(
                        userEmail, userPassword //, isRememberValid
                    )
                }
            }

            is DoctorAuthEvent.OnPopBack -> {
                sendUiEvent(UIEvent.PopBackStack)
            }

            else -> Unit
        }
    }


    private fun getLoginResponse(phone: String, password: String) {
        viewModelScope.launch {
            repository
                .doctorLogin(phone, password)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            state = state.copy(isLoading = false)
                            sendUiEvent(UIEvent.ShowSnackbar(result.data.toString()))
                            sendUiEvent(UIEvent.Navigate(Routes.DOCTOR_HOME_SCREEN))
                        }

                        is Resource.Error -> {
                            state = state.copy(isLoading = false)
                            sendUiEvent(UIEvent.ShowSnackbar("${result.error}"))
                        }

                        is Resource.Loading -> {
                            state = state.copy(isLoading = true)
                        }
                    }
                }
        }
    }


    fun getDoctorAppointmentDataList() {
        viewModelScope.launch {
            repository.getDoctorAppointment().collect{result ->
                when(result){
                    is Resource.Error -> Log.e("TAG", "getSettingData: ${result.error}", )
                    is Resource.Loading -> Log.e("TAG", "getSettingData: Loading", )
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            Log.e("TAG", "getSettingData: ${listings.size}", )
                            Log.e("TAG", "getSettingData: ${listings}", )
                            /*appointmentState = appointmentState.copy(
                                companies = listings
                            )*/
                        }
                    }
                }
            }
        }
    }
}

data class DoctorLoginState(
    val companies: String = "",
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)