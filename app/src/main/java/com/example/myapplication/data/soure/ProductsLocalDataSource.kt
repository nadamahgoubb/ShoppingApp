package com.example.myapplication.data.soure

import com.example.myapplication.data.entitiy.BannerModel
import com.example.myapplication.data.entitiy.ProductModel
import com.example.myapplication.data.soure.local.RoomDao
import com.example.myapplication.data.soure.remote.ApiInterface
import com.example.myapplication.domain.IProductsDataSource
import retrofit2.Response


class ProductsLocalDataSource  @Inject constructor(
    private val roomDao: RoomDao
) :IProductsDataSource{
    override suspend fun getProduct(): Response<ProductModel> {
        TODO("Not yet implemented")
    }


    override suspend fun getBanners(): Response<BannerModel> {
        TODO("Not yet implemented")
    }

}





