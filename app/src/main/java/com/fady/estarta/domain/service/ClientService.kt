package com.fady.estarta.domain.service

import com.fady.estarta.data.models.ProductsResponse
import retrofit2.http.GET

interface ClientService {

    @GET("dynamodb-writer")
    suspend fun getProducts(): ProductsResponse

}