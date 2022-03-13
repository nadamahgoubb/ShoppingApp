package com.example.myapplication.data.network

enum class ApiBase(baseurl: String) {
    BASE_URL("https://student.valuxapps.com/api/");

    var baseUrl = "https://student.valuxapps.com/api/"

    override fun toString(): String {
        return baseUrl
    }

    init {
        baseUrl = baseurl
    }
}
