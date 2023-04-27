package com.hanif.medical.viewmodel

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.hanif.medical.models.Resource
import com.hanif.medical.repository.AuthRepository
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModal() {

    private val _registerFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val registerFlow: StateFlow<Resource<FirebaseUser>?> = _registerFlow

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    init {
        if (authRepository.currentUser() != null){
            _loginFlow.value = Resource.Success(authRepository.currentUser()!!)
        }
    }

    fun register(
        name: String,
        email: String,
        phone: String,
        password: String
    ) = viewModelScope.launch {
        _registerFlow.value = Resource.Loading()
        val result = authRepository.register(name, email, phone, password)
        _registerFlow.value = result
    }

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading()
        val result = authRepository.login(email, password)
        _loginFlow.value = result
    }

    fun logout(){
        authRepository.logout()
        _registerFlow .value = null
        _loginFlow.value = null
    }

    val currentUser :FirebaseUser?
        get() = authRepository.currentUser()
}