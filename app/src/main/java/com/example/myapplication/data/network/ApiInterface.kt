package com.example.myapplication.data.network

import com.example.myapplication.Resource
import com.example.myapplication.data.entitiy.BannerModel
import com.example.myapplication.data.entitiy.ProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {
   /* //Categories
    @GET("categories")
    suspend fun getCategories(@Header("lang") lang: String): MainModel

    //banners
    @GET("banners")
    suspend fun getBanners(): BannerModel
*/
    //Products
    @GET("products")
    suspend fun getProducts(@Header("lang") lang: String): Response<ProductModel>

    @GET("banners")
    suspend fun getBanners(): Response<BannerModel>

    @GET("products")
    suspend fun getProductsCategory(
        @Header("lang") lang: String,
        @Query("category_id") category_id: Int
    ): ProductModel


}
