package com.hanif.medical.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.hanif.medical.Screens.shopping.ShoppingProcessModel
import com.hanif.medical.models.Resource
import com.hanif.medical.repository.ShoppingRepository
import com.hanif.medical.viewmodel.base.BaseViewModal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(private val repository: ShoppingRepository) :
    BaseViewModal() {

    init {
        getOrderList()
    }

    var state by mutableStateOf(OrderListState())
    suspend fun insertShoppingOrder(shoppingProcessModel: ShoppingProcessModel) =
        repository.insertShoppingOrder(shoppingProcessModel)

    private fun getOrderList() {
        viewModelScope.launch {
            repository.getOrderList().collect { result ->
                when (result) {
                    is Resource.Error -> Log.e("TAG", "getSettingData: ${result.error}")
                    is Resource.Loading -> Log.e("TAG", "getSettingData: Loading")
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            state = state.copy(companies = result.data)
                            Log.e("TAG", "OrderList: ${listings.size}")
                            Log.e("TAG", "OrderList: ${listings}")
                        }
                    }
                }
            }
        }
    }
}

data class OrderListState(
    val companies: List<ShoppingProcessModel> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)