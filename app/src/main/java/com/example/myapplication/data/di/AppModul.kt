package com.example.myapplication.data.di

import com.example.myapplication.data.soure.DefaultRepo
import com.example.myapplication.data.soure.ProductsLocalDataSource
import com.example.myapplication.data.soure.ProductsRemoteDataSource
import com.example.myapplication.data.soure.remote.ApiInterface
import com.example.myapplication.domain.IProductsDataSource
import com.example.myapplication.domain.IProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModul() {

    @Singleton
    @RemoteProductDataSource
    @Provides
    fun provideRemoteProductsDataSource(
        api: ApiInterface
    ): IProductsDataSource = ProductsRemoteDataSource(api)

/*
    @Singleton
    @Provides
    fun provideIProductsDataSource(
        api: ApiInterface
    ): IProductsDataSource = ProductsRemoteDataSource(api)
*/


    @Singleton
    @Provides
    fun provideDefaultProductRepository(
        @RemoteProductDataSource remote: IProductsDataSource,
        @LocalProductDataSource local: IProductsDataSource
    ) = DefaultRepo(remote, local) as IProductsRepository

    @LocalProductDataSource
    @Singleton
    @Provides

    fun provideLocalProductDataSource(
    ): IProductsDataSource = ProductsLocalDataSource()


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RemoteProductDataSource

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LocalProductDataSource


}