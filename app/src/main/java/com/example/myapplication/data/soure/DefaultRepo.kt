package com.example.myapplication.data.soure

import com.example.myapplication.data.entitiy.BannerModel
import com.example.myapplication.data.entitiy.ProductModel
import com.example.myapplication.data.soure.local.RoomDao
import com.example.myapplication.data.soure.remote.ApiInterface
import com.example.myapplication.domain.IProductsDataSource
import com.example.myapplication.domain.IProductsRepository
import retrofit2.Response


class DefaultRepo @Inject constructor(
    private val productsRemoteDataSource: IProductsDataSource,
    private val productsLocalDataSource: IProductsDataSource,
    // private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IProductsRepository {

    override suspend fun getProduct()=    productsRemoteDataSource.getProduct()



    override suspend fun getBanners()=    productsRemoteDataSource.getBanners()

}





