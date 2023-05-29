package com.hanif.medical.Screens.doctor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hanif.medical.Screens.validation.Validations
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorBookingViewModel @Inject constructor() :
    BaseViewModal() {


    var gender by mutableStateOf("")
        private set
    var genderValid by mutableStateOf(false)
        private set
    var genderErrMsg by mutableStateOf("")
        private set

    var weight by mutableStateOf("")
        private set
    var weightValid by mutableStateOf(false)
        private set
    var weightErrMsg by mutableStateOf("")
        private set

    var height by mutableStateOf("")
        private set
    var heightValid by mutableStateOf(false)
        private set
    var heightErrMsg by mutableStateOf("")
        private set

    var age by mutableStateOf("")
        private set
    var ageValid by mutableStateOf(false)
        private set
    var ageErrMsg by mutableStateOf("")
        private set

    var tabPage by mutableStateOf(TabPage.Self)

    fun onEvent(event: DoctorBookingEvent) {
        when (event) {
            is DoctorBookingEvent.OnAgeChange -> {
                age = event.age
                val result = Validations().heightAndWeightValidation(age, "age")
                ageValid = !result.successful
                if (!result.successful) {
                    ageErrMsg = result.errorMessage!!
                }
            }

            is DoctorBookingEvent.OnGenderChange -> {
                gender = event.gender
                val result = Validations().onlyEmptyCheck(gender, errorName = "gender")
                genderValid = !result.successful
                if (!result.successful) {
                    genderErrMsg = result.errorMessage!!
                }
            }

            is DoctorBookingEvent.OnHeightChange -> {
                height = event.height
                val result = Validations().heightAndWeightValidation(height, "height")
                if (!result.successful) {
                    heightErrMsg = result.errorMessage!!
                }
            }

            DoctorBookingEvent.OnPopBack -> TODO()
            DoctorBookingEvent.OnSubmitClick -> {
                val validateAge = Validations().heightAndWeightValidation(age, "age")
                val validateHeight = Validations().heightAndWeightValidation(height, "height")
                val validateWeight = Validations().heightAndWeightValidation(weight, "weight")
                val validateGender = Validations().onlyEmptyCheck(gender, errorName = "gender")
                val hasError = listOf(
                    validateAge,
                    validateHeight,
                    validateWeight,
                    validateGender,
                ).any { !it.successful }

                if (hasError) {
                    ageValid = !validateAge.successful
                    ageErrMsg = validateAge.errorMessage ?: ""
                    heightValid = !validateHeight.successful
                    heightErrMsg = validateHeight.errorMessage ?: ""
                    weightValid = !validateWeight.successful
                    weightErrMsg = validateWeight.errorMessage ?: ""
                    genderValid = !validateGender.successful
                    genderErrMsg = validateGender.errorMessage ?: ""

                    return
                }


                    sendUiEvent(UIEvent.Navigate(Routes.DOCTOR_BOOKING_PROCESS_SECOND_SCREEN))
            }

            is DoctorBookingEvent.OnWeightChange -> {
                weight = event.weight
                val result = Validations().heightAndWeightValidation(weight, "weight")
                weightValid = !result.successful
                if (!result.successful) {
                    weightErrMsg = result.errorMessage!!
                }
            }
        }
    }
}

data class ValidationResult(val successful: Boolean, val errorMessage: String? = null)