package com.fady.estarta.domain.use_case

import com.fady.estarta.data.models.ProductsResponse
import com.fady.estarta.domain.repository.NetworkRepository
import com.fady.estarta.utils.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {

    operator fun invoke():
            Flow<Resource<ProductsResponse>> =
        flow {
            emit(Resource.Loading)
            emit(networkRepository.getProducts())
        }.flowOn(Dispatchers.IO)
}
