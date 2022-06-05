package com.example.myapplication.data.soure

import android.util.Log
import com.example.myapplication.BuildConfig.DEBUG
import com.example.myapplication.data.entitiy.BannerModel
import com.example.myapplication.data.entitiy.ProductModel
import com.example.myapplication.data.soure.remote.ApiInterface
import com.example.myapplication.domain.IProductsDataSource
import retrofit2.Response
import javax.inject.Inject


class ProductsRemoteDataSource @Inject constructor(
    private val apiInterface: ApiInterface
) : IProductsDataSource {

    override suspend fun getProduct(): Response<ProductModel> ?{
        return try {
           return apiInterface.getProducts("en")
        } catch (e: Exception) {
            Log.d("product ",e.message.toString())
            return  null
        }
    }


    override suspend fun getBanners(): Response<BannerModel> {
        TODO("Not yet implemented")
    }

}





