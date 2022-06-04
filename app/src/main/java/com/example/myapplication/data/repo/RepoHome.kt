package com.example.myapplication.data.repo

import android.content.Context
import com.example.myapplication.data.entitiy.BannerModel
import com.example.myapplication.data.entitiy.ProductModel
import com.example.myapplication.data.network.ApiBase
import com.example.myapplication.data.network.ApiInterface
import retrofit2.Response

class RepoHome(context: Context) {

    private var apiClient: ApiClient? = null
    private var apiInterface: ApiInterface? = null


    init {
            apiClient = ApiClient()
        apiInterface =
            apiClient!!.getClient(ApiBase.baseUrl)!!.create(ApiInterface::class.java)
       // generalDb= GeneralDataBase.getDataClient(context)
    }



    suspend fun getProduct(): Response<ProductModel>{
        return apiInterface!!.getProducts("en")
    }
    suspend fun getBanners(): Response<BannerModel>{
        return apiInterface!!.getBanners()
    }


}
