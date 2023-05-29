package com.hanif.medical.Screens.doctor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.hanif.medical.repository.DoctorModel
import com.hanif.medical.repository.MedicineModel
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorSharedViewModel @Inject constructor() :BaseViewModal(){

    var doctorModel by mutableStateOf<DoctorModel?>(null)
        private set

    fun addDoctorModel(model: DoctorModel){
        doctorModel = model
    }
    var bookingProcessModel by mutableStateOf<BookingProcess?>(null)
        private set

    fun addBookingProcess(model: BookingProcess){
        bookingProcessModel = model
    }


}



data class BookingProcess(
    val model: DoctorModel? = null  ,
    val bookingType :String = "",
    val appointmentType: String = "",
    val basic_gender:String = "",
    val height :String = "",
    val weight :String = "",
    val age :String = "",
    val date :String = "",
    val time :String = "",
    val paymentOption:String = ""
)