package com.fady.estarta.di

import com.fady.estarta.data.datasource.ProductsRemoteDataSource
import com.fady.estarta.data.repository.NetworkRepositoryImpl
import com.fady.estarta.domain.repository.NetworkRepository
import com.fady.estarta.domain.service.ClientService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

    @Provides
    @Singleton
    fun provideProductRepository(productsRemoteDataSource: ProductsRemoteDataSource): NetworkRepository =
        NetworkRepositoryImpl(productsRemoteDataSource)


    @Provides
    @Singleton
    fun provideProductServices(retrofit: Retrofit): ClientService =
        retrofit.create(ClientService::class.java)
}