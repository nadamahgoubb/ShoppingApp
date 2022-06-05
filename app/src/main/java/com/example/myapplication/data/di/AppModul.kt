package com.example.myapplication.data.di

import com.example.myapplication.data.soure.ProductsRemoteDataSource
import com.example.myapplication.data.soure.remote.ApiInterface
import com.example.myapplication.domain.IProductsDataSource

class AppModul {

    @Singleton
    @Provides
    fun provideRemoteProductsDataSource(
        api: ApiInterface
    ): IProductsDataSource = ProductsRemoteDataSource(api)

}