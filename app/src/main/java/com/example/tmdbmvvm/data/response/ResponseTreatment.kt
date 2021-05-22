package com.example.tmdbmvvm.data.response

import com.example.tmdbmvvm.model.GeneroModel.GenerosList
import com.example.tmdbmvvm.model.GeneroModel.GenreCache
import com.example.tmdbmvvm.model.moviemodel.MovieDetail
import com.example.tmdbmvvm.model.similarmoviemodel.ResultSimilarMovies
import com.example.tmdbmvvm.model.similarmoviemodel.SimilarMoviesModel
import com.example.tmdbmvvm.utils.Constants.Api.BASE_URL_ORIGINAL_IMAGE
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

        return if (response is GetResponseApi.ResponseSucess) {
            val movie = response.data as MovieDetail?
            movie?.posterPath = movie?.getFullPosterPath().toString()
            GetResponseApi.ResponseSucess(movie)

        } else {
            response
        }
    }

    suspend fun getResponseApiSimilarMovie(movieId: Int): GetResponseApi {
        val moviesWithGenres: List<ResultSimilarMovies?>?
        val response = responseCall.responseCallSimilar(movieId)

        return if (response is GetResponseApi.ResponseSucess) {
            val movie = response.data as SimilarMoviesModel?

            movie?.resultSimilarMovies.let {
                moviesWithGenres = it?.map { movie ->
                    movie?.posterPath = "$BASE_URL_ORIGINAL_IMAGE${movie?.posterPath}"
                    movie?.copy(genres = genreCache.genres.filter { movie.genreIds.contains(it.id) })
                }
            }
            GetResponseApi.ResponseSucess(moviesWithGenres)

        } else {
            response
        }
    }

    suspend fun getResponseApiGenre(): GetResponseApi {
        val response = responseCall.responseCallGenre()
        return if (response is GetResponseApi.ResponseSucess) {
            val listOfGenre = response.data as GenerosList

            listOfGenre.let {
                genreCache.cacheGenres(it.genres)
            }
            GetResponseApi.ResponseSucess(listOfGenre)

        } else {
            response
        }
    }
}