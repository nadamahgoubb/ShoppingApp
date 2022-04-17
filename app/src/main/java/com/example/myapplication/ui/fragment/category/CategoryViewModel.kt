package com.example.myapplication.ui.fragment.category

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Resource
import com.example.myapplication.data.entitiy.category.CategoryModel
import com.example.myapplication.data.entitiy.category.DataX
import com.example.myapplication.data.local.RoomDao
import com.example.myapplication.data.repo.CategoryRepo
import com.example.myapplication.utils.NetworkConnectivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(var room: RoomDao ,var repo: CategoryRepo) : ViewModel() {

    private lateinit var context: Context
    fun setActivity(context: Context) {
        this.context = context
    }


    var categoryList: MutableLiveData<Resource<List<DataX>>> = MutableLiveData()
    var response: List<DataX>? = null


    fun gerCategory(): MutableLiveData<Resource<List<DataX>>> {
        //  categoryList.postValue(Resource.Loading())
        try {
            if (NetworkConnectivity.hasInternetConnection(context)) {
                viewModelScope.launch {
                    val response = repo.getCategories()
                    categoryList.postValue(handleResponse(response))
                }
            } else {
                categoryList.postValue(Resource.Error("No internet connection"))

            }

        } catch (e: Exception) {
            when (e) {
                is IOException -> categoryList.postValue(Resource.Error("Network Failure"))
                else -> categoryList.postValue(Resource.Error("Conversion Error"))
            }
        }


        return categoryList
    }


    private fun handleResponse(response: Response<CategoryModel>): Resource<List<DataX>>? {
        if (response.isSuccessful) {
            response.body().let { it ->
                if (this.response == null) {
                    if (it != null) {
                        this.response = it.data?.data
                    }
                }
                return this.response?.let { it1 -> Resource.Success(it1) }!!
            }

        } else return Resource.Error(response.message())

    }

}
