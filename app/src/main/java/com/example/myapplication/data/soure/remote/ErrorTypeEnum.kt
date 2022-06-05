package com.example.myapplication.data.soure.remote

enum class ErrorTypeEnum(val value: Int) {
    noError(0),
    BackendLogicFail(1),
    ServerCodeFail(2),
    InternetConnectionFail(3),
    ConversionIssueFail(4),
    other(5),
    unAuthorized(6);

}
