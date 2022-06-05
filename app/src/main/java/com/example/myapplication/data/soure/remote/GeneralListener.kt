package com.example.myapplication.data.soure.remote

interface GeneralListener<T> {
    fun getApiResponse(
        status: Int, message: String?,
        tApiResponse: T
    )
}



