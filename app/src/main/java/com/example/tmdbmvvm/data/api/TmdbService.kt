package com.example.tmdbmvvm.data.api

import com.example.tmdbmvvm.model.GeneroModel.GenerosList
import com.example.tmdbmvvm.model.moviemodel.MovieDetail
import com.example.tmdbmvvm.model.similarmoviemodel.SimilarMoviesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbService {

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int) : Response<MovieDetail>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int) : Response<SimilarMoviesModel>

    @GET("genre/movie/list")
    suspend fun getGenres() : Response<GenerosList>

}