package com.example.tmdbmvvm.data.response

import com.example.tmdbmvvm.data.api.ApiObject.BASE_URL_ORIGINAL_IMAGE
import com.example.tmdbmvvm.model.genremodel.GenreList
import com.example.tmdbmvvm.model.genremodel.GenreCache
import com.example.tmdbmvvm.model.moviemodel.MovieDetail
import com.example.tmdbmvvm.model.similarmoviemodel.ResultSimilarMovies
import com.example.tmdbmvvm.model.similarmoviemodel.SimilarMoviesModel
import org.koin.core.KoinComponent
import org.koin.core.get


class ResponseTreatment(var responseCall: CallResponse) : KoinComponent {
    private var genreCache: GenreCache

    init {
        responseCall = get<CallResponse>()
        genreCache = get<GenreCache>()
    }

    suspend fun getResponseApiMovie(movieId: Int): GetResponseApi {
        val response = responseCall.responseCallMovie(movieId)

        return if (response is GetResponseApi.ResponseSuccess) {
            val movie = response.data as MovieDetail?
            movie?.posterPath = movie?.getFullPosterPath().toString()
            GetResponseApi.ResponseSuccess(movie)

        } else {
            response
        }
    }

    suspend fun getResponseApiSimilarMovie(movieId: Int): GetResponseApi {

        val moviesWithGenres: List<ResultSimilarMovies?>?
        val response = responseCall.responseCallSimilar(movieId)

        return if (response is GetResponseApi.ResponseSuccess) {
            val movie = response.data as SimilarMoviesModel?

            movie?.resultSimilarMovies.let { listSimilarMovies ->
                moviesWithGenres = listSimilarMovies?.map { movie ->
                    movie?.posterPath = "$BASE_URL_ORIGINAL_IMAGE${movie?.posterPath}"
                    movie?.copy(genres = genreCache.genres.filter { movie.genreIds.contains(it.id) })
                }
            }

            GetResponseApi.ResponseSuccess(moviesWithGenres)

        } else {
            response
        }
    }

    suspend fun getResponseApiGenre(): GetResponseApi {
        val response = responseCall.responseCallGenre()
        return if (response is GetResponseApi.ResponseSuccess) {
            val listOfGenre = response.data as GenreList

            listOfGenre.let {
                genreCache.cacheGenres(it.genres)
            }
            GetResponseApi.ResponseSuccess(listOfGenre)

        } else {
            response
        }
    }
}