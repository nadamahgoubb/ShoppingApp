package com.example.myapplication.data.entitiy

data class BannerModel(
    val `data`: List<DataBanner>,
    val message: String,
    val status: Boolean
)

data class DataBanner(
    val category: String,
    val id: Int,
    val image: String,
    val product: String
)