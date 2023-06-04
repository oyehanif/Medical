package com.hanif.medical.Screens.doctorModule

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hanif.medical.Screens.doctor.BookingProcess
import com.hanif.medical.models.Resource
import com.hanif.medical.repository.DoctorAuthRepository
import com.hanif.medical.repository.ReportModel
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorHomeViewModel @Inject constructor(
    private val repository: DoctorAuthRepository
) : BaseViewModal() {

    fun insertReports(reports : ReportModel) = viewModelScope.launch { repository.insertReports(reports) }

}