package com.example.tmdbmvvm.data.response

import com.example.tmdbmvvm.data.api.ApiService
import com.example.tmdbmvvm.data.base.BaseCallResponse

class CallResponse() : BaseCallResponse() {

    suspend fun responseCallMovie(movieId: Int): GetResponseApi {
        val response = apiServiceReturnMovie(movieId)
        return responseBase(response)
    }

    suspend fun responseCallSimilar(movieId: Int): GetResponseApi {
        val response = apiServiceReturnSimilar(movieId)
        return responseBase(response)
    }

    suspend fun responseCallGenre(): GetResponseApi {
        val response = apiServiceReturnGenre()
        return responseBase(response)
    }

    private suspend fun apiServiceReturnMovie(movieId: Int) = ApiService.tmdbApi.getMovie(movieId)
    private suspend fun apiServiceReturnSimilar(movieId: Int) = ApiService.tmdbApi.getSimilarMovies(movieId)
    private suspend fun apiServiceReturnGenre() = ApiService.tmdbApi.getGenres()
}
