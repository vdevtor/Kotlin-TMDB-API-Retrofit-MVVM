package com.example.tmdbmvvm.repository

import com.example.tmdbmvvm.data.response.GetResponseApi

interface Repository {

    suspend fun getMovie(movieId: Int): GetResponseApi
    suspend fun getSimilar(movieId: Int): GetResponseApi
    suspend fun getGenre() : GetResponseApi
}
