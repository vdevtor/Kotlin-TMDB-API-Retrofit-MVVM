package com.example.tmdbmvvm.data.repository

import androidx.appcompat.app.AppCompatActivity
import com.example.tmdbmvvm.data.api.GetResponseApi
import com.example.tmdbmvvm.data.api.CallResponse
import com.example.tmdbmvvm.data.business.ResponseTreatment
import org.koin.android.ext.android.get


class Repository_Imp(var responseTreatment: ResponseTreatment, var responseCall: CallResponse) : Repository, AppCompatActivity() {

    init {
        responseTreatment = get<ResponseTreatment>()
    }

    override suspend fun getMovie(movieId: Int): GetResponseApi {
        return  responseTreatment.getResponseApiMovie(movieId)
    }


    override suspend fun getSimilar(movieId: Int): GetResponseApi {
        return responseTreatment.getResponseApiSimilarMovie(movieId)
    }

    override suspend fun getGenre(genreId: Int): GetResponseApi {
        return responseTreatment.getResponseApiGenre(genreId)
    }
}