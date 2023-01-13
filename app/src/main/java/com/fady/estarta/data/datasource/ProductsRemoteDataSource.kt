package com.fady.estarta.data.datasource

import com.fady.estarta.domain.service.ClientService
import com.fady.estarta.utils.base.BaseRemoteDataSource
import javax.inject.Inject

class ProductsRemoteDataSource @Inject constructor(
    private val apiService: ClientService
) : BaseRemoteDataSource() {

    suspend fun getProducts() = safeApiCall {
        apiService.getProducts()
    }

}