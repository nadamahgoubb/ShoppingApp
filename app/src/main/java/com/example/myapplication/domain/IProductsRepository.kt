package com.example.myapplication.domain

import com.example.myapplication.data.entitiy.BannerModel
import com.example.myapplication.data.entitiy.ProductModel
import retrofit2.Response


interface IProductsRepository {

    suspend fun getProduct(): Response<ProductModel>?
    suspend fun getBanners(): Response<BannerModel>
}

interface IProductsDataSource {

    suspend fun getProduct(): Response<ProductModel>?
    suspend fun getBanners(): Response<BannerModel>
}