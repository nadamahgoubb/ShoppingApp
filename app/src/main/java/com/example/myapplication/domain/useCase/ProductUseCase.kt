package com.example.myapplication.domain.useCase

import com.example.myapplication.data.entitiy.BannerModel
import com.example.myapplication.data.entitiy.ProductModel
import com.example.myapplication.domain.IProductsRepository
import retrofit2.Response


suspend fun showAllProducts(
    productsRepo: IProductsRepository
): Response<ProductModel>? =
    productsRepo.getProduct()


suspend fun showAllBanners(
    productsRepo: IProductsRepository
): Response<BannerModel> =
    productsRepo.getBanners()


