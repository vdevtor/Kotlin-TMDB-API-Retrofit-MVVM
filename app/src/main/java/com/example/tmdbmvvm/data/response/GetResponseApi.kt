package com.example.tmdbmvvm.data.response

abstract class GetResponseApi {
    class ResponseSuccess(val data: Any?) : GetResponseApi()
    class ResponseError(val message: String) : GetResponseApi()
}