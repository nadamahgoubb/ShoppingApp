package com.example.myapplication

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.entitiy.BannerModel
import com.example.myapplication.data.entitiy.ProductModel
import com.example.myapplication.data.network.ApiBase
import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.ApiInterface
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response

class RepoHome(context: Context) {

    private var apiClient: ApiClient? = null
    private var apiInterface: ApiInterface? = null


    init {
        apiClient = ApiClient()
        apiInterface =
            apiClient!!.getClient(ApiBase.BASE_URL)!!.create(ApiInterface::class.java)
       // generalDb= GeneralDataBase.getDataClient(context)
    }
/*
   /* fun getBanners(): MutableStateFlow<List<BannerDatum>> {
        return bannersList
    }
    */
 */


    suspend fun getProduct(): Response<ProductModel>{
        return apiInterface!!.getProducts("en")
    }
    suspend fun getBanners(): Response<BannerModel>{
        return apiInterface!!.getBanners()
    }


}
