package com.hanif.medical.Screens.shopping.shppingaddress

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var address1 by mutableStateOf("")
        private set

    var addressValid1 by mutableStateOf(false)
        private set

    var addressMsg1: String by mutableStateOf("")
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
}