package com.hanif.medical.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.hanif.medical.models.Resource
import com.hanif.medical.repository.DoctorModel
import com.hanif.medical.repository.HomeRepository
import com.hanif.medical.repository.MedicineModel
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val home: HomeRepository) :BaseViewModal() {
    private val _fetchList: MutableStateFlow<List<DoctorModel>?> =
        MutableStateFlow(null)
    val fetchList = _fetchList

    var state by mutableStateOf(DoctorListingsState())
    var medicineState by mutableStateOf(MedicineListingsState())

    init {
        /*viewModelScope.launch { getSettingData() }
        _fetchList.value?.data?.forEach {
            Log.e("TAG", "${it.toString()} ", )
        }*/
        getSettingData()
        getMedicineDataList()
    }

    private  fun getSettingData() {
        viewModelScope.launch {
            home.fetchData().collect{result ->
                when(result){
                    is Resource.Error -> Log.e("TAG", "getSettingData: ${result.error}", )
                    is Resource.Loading -> Log.e("TAG", "getSettingData: Loading", )
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            Log.e("TAG", "getSettingData: ${listings.size}", )
                            Log.e("TAG", "getSettingData: ${listings}", )
                            state = state.copy(
                                companies = listings
                            )
                        }
                    }
                }
            }
        }
    }

    private  fun getMedicineDataList() {
        viewModelScope.launch {
            home.getMedicineList().collect{result ->
                when(result){
                    is Resource.Error -> Log.e("TAG", "getSettingData: ${result.error}", )
                    is Resource.Loading -> Log.e("TAG", "getSettingData: Loading", )
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            Log.e("TAG", "getSettingData: ${listings.size}", )
                            Log.e("TAG", "getSettingData: ${listings}", )
                            medicineState = medicineState.copy(
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

data class MedicineListingsState(
    val companies: List<MedicineModel> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)