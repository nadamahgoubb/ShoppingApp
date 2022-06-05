package com.example.myapplication.data.soure

import com.example.myapplication.data.entitiy.ProductModel
import com.example.myapplication.data.soure.remote.ApiInterface
import com.example.myapplication.domain.IProductsDataSource
import retrofit2.Response


class ProductsRemoteDataSource @Inject constructor(
    private val apiInterface: ApiInterface
) : IProductsDataSource {

     suspend fun getProducts(): Response<ProductModel>? {
        return try {
            apiInterface.getProducts("en")
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getProduct(): Response<ProductModel> {
        TODO("Not yet implemented")
    }

}





