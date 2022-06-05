package com.example.myapplication.data.soure

import com.example.myapplication.domain.IProductsDataSource
import com.example.myapplication.domain.IProductsRepository
import javax.inject.Inject


class DefaultRepo @Inject constructor(
    private val productsRemoteDataSource: IProductsDataSource,
    private val productsLocalDataSource: IProductsDataSource,
    // private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IProductsRepository {

    override suspend fun getProduct()= productsRemoteDataSource.getProduct()

    override suspend fun getBanners()=    productsRemoteDataSource.getBanners()

}





