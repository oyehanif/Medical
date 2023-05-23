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
}