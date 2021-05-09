package com.example.tmdbmvvm.data.repository

import com.example.tmdbmvvm.data.api.GetResponseApi

interface Repository {

    suspend fun getMovie(movieId: Int): GetResponseApi
    suspend fun getSimilar(movieId: Int): GetResponseApi
    suspend fun getGenre(genreId: Int) : GetResponseApi
}
