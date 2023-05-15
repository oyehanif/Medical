package com.hanif.medical.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanif.medical.datastore.DataStore
import com.hanif.medical.navigation.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val dataStore: DataStore
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<Boolean> = mutableStateOf(false)
    val startDestination: State<Boolean> = _startDestination

    init {
        viewModelScope.launch {
            dataStore.readOnBoardingState().collect { completed ->
                    _startDestination.value = completed
            }
            _isLoading.value = false
        }
    }

}