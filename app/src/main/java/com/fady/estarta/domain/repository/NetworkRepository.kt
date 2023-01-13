package com.fady.estarta.domain.repository

import com.fady.estarta.data.models.ProductsResponse
import com.fady.estarta.utils.common.Resource

interface NetworkRepository {

    suspend fun getProducts() : Resource<ProductsResponse>

}