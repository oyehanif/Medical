package com.hanif.medical.Screens.shopping.shppingaddress

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.hanif.medical.Screens.validation.ValidateContact
import com.hanif.medical.Screens.validation.Validations
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingAddressViewModel @Inject constructor() : BaseViewModal() {
    var name by mutableStateOf("")
        private set

    var nameValid by mutableStateOf(false)
        private set

    var nameErrMsg: String by mutableStateOf("")
        private set

    var address by mutableStateOf("")
        private set

    var addressValid by mutableStateOf(false)
        private set

    var addressMsg: String by mutableStateOf("")
        private set

    var area by mutableStateOf("")
        private set

    var areaValid by mutableStateOf(false)
        private set

    var areaMsg1: String by mutableStateOf("")
        private set

    var landMark by mutableStateOf("")
        private set

    var landMarkValid by mutableStateOf(false)
        private set

    var landMarkMsg: String by mutableStateOf("")
        private set

    var pinCode by mutableStateOf("")
        private set

    var pinCodeValid by mutableStateOf(false)
        private set

    var pinCodeMsg: String by mutableStateOf("")
        private set

    var city by mutableStateOf("")
        private set

    var cityValid by mutableStateOf(false)
        private set

    var cityMsg: String by mutableStateOf("")
        private set


    var state by mutableStateOf("")
        private set

    var stateValid by mutableStateOf(false)
        private set

    var stateMsg: String by mutableStateOf("")
        private set


    var country by mutableStateOf("")
        private set

    var countryValid by mutableStateOf(false)
        private set

    var countryMsg: String by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    var phoneNameValid by mutableStateOf(false)
        private set

    var phoneErrMsg by mutableStateOf("")
        private set

    var isCheckedTC by mutableStateOf(false)
        private set
    var isCheckedValid by mutableStateOf(false)
        private set

    var checkboxError by mutableStateOf("")
        private set


    fun onEvent(event: ShoppingAddressEvent) {
        when (event) {
            is ShoppingAddressEvent.AreaStreet -> {
                area = event.AreaStreet
                val result = Validations().onlyEmptyCheck(area,"area")
                areaValid = !result.successful
                if (!result.successful) {
                    areaMsg1 = result.errorMessage!!
                }
            }

            is ShoppingAddressEvent.Country -> {
                country = event.country
                val result = Validations().onlyEmptyCheck(country,"country")
                countryValid = !result.successful
                if (!result.successful) {
                    countryMsg = result.errorMessage!!
                }
            }

            is ShoppingAddressEvent.FlatHouse -> {
                address = event.FlatHouse
                val result = Validations().onlyEmptyCheck(address,"FlatHouse")
                addressValid = !result.successful
                if (!result.successful) {
                    addressMsg = result.errorMessage!!
                }
            }

            is ShoppingAddressEvent.FullName -> {
                name = event.fullName
                val result = Validations().onlyEmptyCheck(name,"Full Name")
                nameValid = !result.successful
                if (!result.successful) {
                    nameErrMsg = result.errorMessage!!
                }
            }

            is ShoppingAddressEvent.Landmark -> {
                landMark = event.Landmark
                val result = Validations().onlyEmptyCheck(landMark,"landMark")
                landMarkValid = !result.successful
                if (!result.successful) {
                    landMarkMsg = result.errorMessage!!
                }
            }

            is ShoppingAddressEvent.Mobile -> {
                phone = event.Mobile
                val result = ValidateContact().execute(phone)
                phoneNameValid = !result.successful
                if (!result.successful) {
                    phoneErrMsg = result.errorMessage!!
                }
            }


            is ShoppingAddressEvent.PinCode -> {
                pinCode = event.PinCode
                val result = Validations().onlyEmptyCheck(pinCode,"pinCode")
                pinCodeValid = !result.successful
                if (!result.successful) {
                    pinCodeMsg = result.errorMessage!!
                }
            }

            is ShoppingAddressEvent.State -> {
                state = event.State
                val result = Validations().onlyEmptyCheck(state,"state")
                stateValid = !result.successful
                if (!result.successful) {
                    stateMsg = result.errorMessage!!
                }
            }

            is ShoppingAddressEvent.TownCity -> {
                city = event.TownCity
                val result = Validations().onlyEmptyCheck(city,"city")
                cityValid = !result.successful
                if (!result.successful) {
                    cityMsg = result.errorMessage!!
                }
            }

            ShoppingAddressEvent.OnSubmitClick -> {

                val cityy = Validations().onlyEmptyCheck(city,"city")
                val statee = Validations().onlyEmptyCheck(state,"state")
                val pinCodee = Validations().onlyEmptyCheck(pinCode,"pinCode")
                val phonee = ValidateContact().execute(phone)
                val landMarkk = Validations().onlyEmptyCheck(landMark,"landMark")
                val namee = Validations().onlyEmptyCheck(name,"Full Name")
                val addresss = Validations().onlyEmptyCheck(address,"FlatHouse")
                val countryy = Validations().onlyEmptyCheck(country,"country")
                val areaa = Validations().onlyEmptyCheck(area,"area")

                val hasError = listOf(
                    cityy,
                    statee,
                    pinCodee,
                    phonee,
                    landMarkk,
                    namee,
                    addresss,
                    countryy,
                    areaa,
                ).any { !it.successful }

                if (hasError) {
                    cityValid = !cityy.successful
                    cityMsg = cityy.errorMessage ?: ""
                    stateValid = !statee.successful
                    stateMsg = statee.errorMessage ?: ""
                    pinCodeValid = !pinCodee.successful
                    pinCodeMsg = pinCodee.errorMessage ?: ""
                    phoneNameValid = !phonee.successful
                    phoneErrMsg= phonee.errorMessage ?: ""
                    landMarkValid = !landMarkk.successful
                    landMarkMsg = landMarkk.errorMessage ?: ""
                    nameValid = !namee.successful
                    nameErrMsg = namee.errorMessage ?: ""
                    addressValid = !addresss.successful
                    addressMsg = addresss.errorMessage ?: ""
                    countryValid = !countryy.successful
                    countryMsg = countryy.errorMessage ?: ""
                    areaValid = !areaa.successful
                    areaMsg1 = areaa.errorMessage ?: ""

                    return
                }
                sendUiEvent(UIEvent.Navigate(Routes.SHOPPING_PRE_PAYMENT_SCREEN))
            }
            ShoppingAddressEvent.OnPopBackstack -> {
            sendUiEvent(UIEvent.PopBackStack)
            }
            else -> Unit
        }
    }
}