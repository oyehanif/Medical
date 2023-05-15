package com.hanif.medical.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hanif.medical.models.Resource
import com.hanif.medical.repository.DoctorModel
import com.hanif.medical.repository.HomeRepository
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


    init {
        /*viewModelScope.launch { getSettingData() }
        _fetchList.value?.data?.forEach {
            Log.e("TAG", "${it.toString()} ", )
        }*/
        getSettingData()
    }

    private  fun getSettingData() = home.fetchData()
}