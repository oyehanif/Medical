package com.hanif.medical.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.hanif.medical.Screens.doctor.BookingProcess
import com.hanif.medical.models.Resource
import com.hanif.medical.repository.DoctorModel
import com.hanif.medical.repository.HomeRepository
import com.hanif.medical.repository.MedicineModel
import com.hanif.medical.repository.NotificationModel
import com.hanif.medical.repository.ReportModel
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val home: HomeRepository) : BaseViewModal() {
    private val _fetchList: MutableStateFlow<List<DoctorModel>?> = MutableStateFlow(null)
    val fetchList = _fetchList

    var state by mutableStateOf(DoctorListingsState())
    var medicineState by mutableStateOf(MedicineListingsState())
    var appointmentState by mutableStateOf(AppointmentListingsState())
    var reportState by mutableStateOf(ReportListingsState())
    var notificationState by mutableStateOf(NotificationListingsState())

    init {/*viewModelScope.launch { getSettingData() }
        _fetchList.value?.data?.forEach {
            Log.e("TAG", "${it.toString()} ", )
        }*/
        getSettingData()
        getMedicineDataList()
        getAppointmentDataList()
        getReportList()
        getNotificationList()
    }

    fun getSettingData() {
        viewModelScope.launch {
            home.fetchData().collect { result ->
                when (result) {
                    is Resource.Error -> Log.e("TAG", "getSettingData: ${result.error}")
                    is Resource.Loading -> state = state.copy(isLoading = true)
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            Log.e("TAG", "getSettingData: ${listings.size}")
                            Log.e("TAG", "getSettingData: ${listings}")
                            state = state.copy(
                                companies = listings
                            )
                            state = state.copy(isLoading = false)
                        }
                    }
                }
            }
        }
    }

    suspend fun insertAppointment(bookingProcess: BookingProcess, isUpdate: Boolean = false) =
        home.insertAppointment(bookingProcess, isUpdate)

    private fun getMedicineDataList() {
        viewModelScope.launch {
            home.getMedicineList().collect { result ->
                when (result) {
                    is Resource.Error -> Log.e("TAG", "getSettingData: ${result.error}")
                    is Resource.Loading -> Log.e("TAG", "getSettingData: Loading")
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            Log.e("TAG", "getSettingData: ${listings.size}")
                            Log.e("TAG", "getSettingData: ${listings}")
                            medicineState = medicineState.copy(
                                companies = listings
                            )
                        }
                    }
                }
            }
        }
    }

     fun getAppointmentDataList() {
        viewModelScope.launch {
            home.getUserAppointment().collect { result ->
                when (result) {
                    is Resource.Error -> Log.e("TAG", "getSettingData: ${result.error}")
                    is Resource.Loading -> Log.e("TAG", "getSettingData: Loading")
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            Log.e("TAG", "getSettingData: ${listings.size}")
                            Log.e("TAG", "getSettingData: ${listings}")
                            appointmentState = appointmentState.copy(
                                companies = listings
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getReportList() {
        viewModelScope.launch {
            home.getUserReports().collect { result ->
                when (result) {
                    is Resource.Error -> reportState =
                        reportState.copy(isLoading = false, searchQuery = result.error!!)

                    is Resource.Loading -> reportState = reportState.copy(isLoading = true)
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            Log.e("TAG", "getReportList: ${listings.size}")
                            Log.e("TAG", "getReportList: ${listings}")
                            reportState = reportState.copy(
                                companies = listings, isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getNotificationList() {
        viewModelScope.launch {
            home.getNotification().collect { result ->
                when (result) {
                    is Resource.Error -> notificationState =
                        notificationState.copy(isLoading = false, searchQuery = result.error!!)

                    is Resource.Loading -> notificationState = notificationState.copy(isLoading = true)
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            Log.e("TAG", "getSettingData: ${listings.size}")
                            Log.e("TAG", "getSettingData: ${listings}")
                            notificationState = notificationState.copy(
                                companies = listings
                            )
                        }
                    }
                }
            }
        }
    }
}

data class DoctorListingsState(
    val companies: List<DoctorModel> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)

data class AppointmentListingsState(
    val companies: List<BookingProcess> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)

data class MedicineListingsState(
    val companies: List<MedicineModel> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)

data class ReportListingsState(
    val companies: List<ReportModel> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)

data class NotificationListingsState(
    val companies: List<NotificationModel> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)