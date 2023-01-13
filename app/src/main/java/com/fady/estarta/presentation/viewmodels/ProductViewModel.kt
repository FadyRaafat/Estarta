package com.fady.estarta.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.fady.estarta.data.models.ProductsResponse
import com.fady.estarta.domain.use_case.GetProductsUseCase
import com.fady.estarta.utils.base.BaseViewModel
import com.fady.estarta.utils.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private var getProductsUseCase: GetProductsUseCase
) : BaseViewModel() {

    // Current Weather
    private var getProductsResponse: ProductsResponse? = null
    private val _getProductsSuccess = MutableSharedFlow<Boolean>()
    val getProductsSuccess = _getProductsSuccess

    fun getProducts() {
        getProductsUseCase()
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        showLoading.value = true
                    }
                    is Resource.Success -> {
                        showLoading.value = false
                        getProductsResponse = result.value
                        _getProductsSuccess.emit(true)
                    }
                    is Resource.Failure -> {
                        _getProductsSuccess.emit(false)
                        showLoading.value = false
                        showApiError.value = result
                    }
                    else -> {}
                }
            }
            .launchIn(viewModelScope)
    }

    fun getProductsResponse() = getProductsResponse

}