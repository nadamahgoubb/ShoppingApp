package com.example.myapplication.data.network

interface GeneralListener<T> {
    fun getApiResponse(
        status: Int, message: String?,
        tApiResponse: T
    )
}



