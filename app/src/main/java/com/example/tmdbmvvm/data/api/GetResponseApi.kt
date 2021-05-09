package com.example.tmdbmvvm.data.api

abstract class GetResponseApi {
    class ResponseSucess(val data: Any?) : GetResponseApi()
    class ResponseError(val message: String) : GetResponseApi()
}