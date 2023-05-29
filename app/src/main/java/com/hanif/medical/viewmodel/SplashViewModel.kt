package com.hanif.medical.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.hanif.medical.Screens.doctor.DoctorSharedViewModel
import com.hanif.medical.datastore.DataStore
import com.hanif.medical.navigation.Screen
import com.hanif.medical.repository.AuthRepository
import com.hanif.medical.repository.HomeRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val dataStore: DataStore,private val authRepository: AuthRepository,private val repository: HomeRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<Boolean> = mutableStateOf(false)
    val startDestination: State<Boolean> = _startDestination

    private val _currentUser: MutableState<FirebaseUser?> = mutableStateOf(null)
    val currentUser: State<FirebaseUser?> = _currentUser

    init {
        viewModelScope.launch {
            _startDestination.value  = dataStore.readOnBoardingState().first()
            authRepository.currentUser()
            _isLoading.value = false
            repository.fetchData()
        }
    }

}