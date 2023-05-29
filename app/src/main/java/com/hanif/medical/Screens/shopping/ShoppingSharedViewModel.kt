package com.hanif.medical.Screens.shopping

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.hanif.medical.repository.MedicineModel
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingSharedViewModel @Inject constructor() : BaseViewModal() {

    var medicalModel by mutableStateOf<MedicineModel?>(null)
        private set

    fun addMedicalValue(model: MedicineModel){
        medicalModel = model
    }


    var shoppingProcessModel by mutableStateOf<ShoppingProcessModel?>(null)
        private set

    fun addShoppingProcessModelValue(model: ShoppingProcessModel){
        shoppingProcessModel = model
    }
}


data class ShoppingProcessModel(
    val qty : Int = 0,
    val fullName:String = "",
    val mobileNumber:String = "",
    val flat_house:String = "",
    val area_street:String = "",
    val landmark:String = "",
    val pin_code:String = "",
    val town_city:String = "",
    val state:String = "",
    val country:String = "",
    val paymentOption:String = "",
)