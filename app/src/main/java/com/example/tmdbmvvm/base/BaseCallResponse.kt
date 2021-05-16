package com.example.tmdbmvvm.base

import com.example.tmdbmvvm.data.api.GetResponseApi
import retrofit2.Response


open class BaseCallResponse() {

    fun <T> ResponseBase(myFunction: Response<T>): GetResponseApi {
        val response = myFunction

        return try {
            if (response.isSuccessful) {
                GetResponseApi.ResponseSucess(response.body())
            } else {
                if (response.code() == 404) {
                    GetResponseApi.ResponseError("Dado não Encontrado")
                } else {
                    GetResponseApi.ResponseError("Erro ao carregar os Dados")
                }
            }
        } catch (exception: Exception) {
            GetResponseApi.ResponseError("Erro ao carergar os dados")
        }
    }
}