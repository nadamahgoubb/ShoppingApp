package com.example.myapplication.ui.fragment.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Resource
import com.example.myapplication.data.entitiy.*
import com.example.myapplication.data.soure.local.RoomDao
import com.example.myapplication.domain.IProductsRepository
import com.example.myapplication.domain.useCase.showAllBanners
import com.example.myapplication.domain.useCase.showAllProducts
import com.example.myapplication.utils.NetworkConnectivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var room: RoomDao,
    private val productsRepository: IProductsRepository
) : ViewModel() {
   // private lateinit var repo: RepoHome
    private lateinit var context: Context

    var productsList: MutableLiveData<Resource<List<DataX>>> = MutableLiveData()
    var productResponse: List<DataX>? = null


    var bannerList: MutableStateFlow<Resource<List<DataBanner>>> = MutableStateFlow(Resource.Loading())

    var bannerResponse: List<DataBanner>? = null



    fun setActivity(context: Context) {
        this.context = context
 //       repo = RepoHome(context)
    }

    suspend fun getBanners(): MutableStateFlow<Resource<List<DataBanner>>> {
    //   productsList.postValue(Resource.Loading())
        try {
            if (NetworkConnectivity.hasInternetConnection(context)) {
                viewModelScope.launch {
                    val response = showAllBanners(productsRepository)
                    bannerList.emit(handleBannerResponse(response))
                }
            } else {
                bannerList.emit(Resource.Error("No internet connection"))

            }

        } catch (e: Exception) {
            when (e) {
                is IOException -> bannerList.emit(Resource.Error("Network Failure"))
                else -> bannerList.emit(Resource.Error("Conversion Error"))
            }
        }


        return bannerList
    }

    private fun handleBannerResponse(response: Response<BannerModel>): Resource<List<DataBanner>>{
        if (response.isSuccessful) {
            response.body().let { it ->
                if (bannerResponse == null) {
                    if (it != null) {
                        bannerResponse = it.data
                    }
                }
                return bannerResponse?.let { it1 -> Resource.Success(it1) }!!
            }

        }else return Resource.Error(response.message())

    }


    fun getProducts(): MutableLiveData<Resource<List<DataX>>> {
      //  productsList.postValue(Resource.Loading())
        try {
            if (NetworkConnectivity.hasInternetConnection(context)) {
                viewModelScope.launch {
                    val response = showAllProducts(productsRepository)

                  //  val response = repo.getProduct()
                    productsList.postValue(handleResponse(response))
                }
            } else {
             productsList.postValue(Resource.Error("No internet connection"))

            }

        } catch (e: Exception) {
            when (e) {
                is IOException -> productsList.postValue(Resource.Error("Network Failure"))
                else -> productsList.postValue(Resource.Error("Conversion Error"))
            }
        }


        return productsList
    }


    private fun handleResponse(response: Response<ProductModel>): Resource<List<DataX>>? {
        if (response.isSuccessful) {
            response.body().let { it ->
                if (productResponse == null) {
                    if (it != null) {
                        productResponse = it.data?.data
                    }
                }
                return productResponse?.let { it1 -> Resource.Success(it1) }!!
            }

        }else return Resource.Error(response.message())

    }
    fun addToCart(cartItem: CartDataEntity) {
      GlobalScope.launch {
          room.insertInCart(cartItem)
      }
    }

    fun insertFav(productItem:DataX) {
        GlobalScope.launch {
            room.insertProductFav(productItem)
        }    }

    fun deletFav(productItem: DataX) {
        GlobalScope.launch {
            room.deleteProductFav(productItem)

        }
    }

}
