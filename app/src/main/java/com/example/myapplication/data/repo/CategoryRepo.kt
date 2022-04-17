package com.example.myapplication.data.repo


import com.example.myapplication.data.entitiy.category.CategoryModel
import com.example.myapplication.data.network.ApiInterface
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepo @Inject constructor(private var api: ApiInterface) {

    suspend fun getCategories(): Response<CategoryModel> {
        return api!!.getCategories("en")
    }

}