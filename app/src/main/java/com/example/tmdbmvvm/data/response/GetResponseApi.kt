package com.example.tmdbmvvm.data.response

abstract class GetResponseApi {
    class ResponseSucess(val data: Any?) : GetResponseApi()
    class ResponseError(val message: String) : GetResponseApi()
}