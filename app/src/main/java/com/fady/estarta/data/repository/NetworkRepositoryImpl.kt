package com.fady.estarta.data.repository

import com.fady.estarta.data.datasource.ProductsRemoteDataSource
import com.fady.estarta.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val productsRemoteDataSource: ProductsRemoteDataSource
) : NetworkRepository {

    override
    suspend fun getProducts() = productsRemoteDataSource.getProducts()
}