package com.example.tmdbmvvm.data.api

import com.example.tmdbmvvm.base.BaseCallResponse

class CallResponse() : BaseCallResponse() {

    suspend fun responseCallMovie(movieId: Int):GetResponseApi{
        val response = apiServiceReturnMovie(movieId)
        return ResponseBase(response)
    }

    suspend fun responseCallSimilar(movieId: Int): GetResponseApi {
        val response = apiServiceReturnSimilar(movieId)
        return ResponseBase(response)
    }

    suspend fun responseCallGenre(genreId: Int): GetResponseApi {
        val response = apiServiceReturnGenre(genreId)
        return ResponseBase(response)
    }

    private suspend fun apiServiceReturnMovie(movieId: Int) = ApiService.tmdbApi.getMovie(movieId)
    private suspend fun apiServiceReturnSimilar(movieId: Int) = ApiService.tmdbApi.getSimilarMovies(movieId)
    private suspend fun apiServiceReturnGenre(genreId: Int) = ApiService.tmdbApi.getGenres(genreId)
}
