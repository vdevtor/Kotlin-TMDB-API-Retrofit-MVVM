package com.example.tmdbmvvm.data.business

import androidx.appcompat.app.AppCompatActivity
import com.example.tmdbmvvm.data.api.GetResponseApi
import com.example.tmdbmvvm.data.api.CallResponse
import com.example.tmdbmvvm.data.model.GeneroModel.Generos
import com.example.tmdbmvvm.data.model.moviemodel.MovieDetail
import com.example.tmdbmvvm.data.model.similarmoviemodel.SimilarMoviesModel
import com.example.tmdbmvvm.utils.Constants.Api.BASE_URL_ORIGINAL_IMAGE
import org.koin.android.ext.android.get

class ResponseTreatment(var responseCall: CallResponse) : AppCompatActivity() {

    suspend fun getResponseApiMovie(movieId: Int): GetResponseApi {
        responseCall = get<CallResponse>()
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
        responseCall = get<CallResponse>()
        val response = responseCall.responseCallSimilar(movieId)
        return if (response is GetResponseApi.ResponseSucess) {
            val movie = response.data as SimilarMoviesModel?
            movie?.resultSimilarMovies?.forEach {
                it?.poster_path = "$BASE_URL_ORIGINAL_IMAGE${it?.poster_path}"
            }
            GetResponseApi.ResponseSucess(movie)

        } else {
            response
        }
    }

    suspend fun getResponseApiGenre(genreId: Int): GetResponseApi {
        responseCall = get<CallResponse>()
        val response = responseCall.responseCallGenre(genreId)
        return if (response is GetResponseApi.ResponseSucess) {
            val listOfGenre = response.data as Generos

            GetResponseApi.ResponseSucess(listOfGenre)

        } else {
            response
        }
    }
}