package com.hanif.medical.viewmodel

import androidx.lifecycle.viewModelScope
import com.hanif.medical.datastore.DataStore
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val dataStore: DataStore) :BaseViewModal(){

    fun saveOnBoardingState(complete :Boolean){
        viewModelScope.launch {
            dataStore.saveOnBoardingState(complete)
        }
    }
}