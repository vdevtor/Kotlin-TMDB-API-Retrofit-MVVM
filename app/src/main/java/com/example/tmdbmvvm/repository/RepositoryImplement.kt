package com.example.tmdbmvvm.repository


import com.example.tmdbmvvm.data.response.GetResponseApi
import com.example.tmdbmvvm.data.response.ResponseTreatment
import org.koin.core.KoinComponent
import org.koin.core.get


class RepositoryImplement(private var responseTreatment: ResponseTreatment) : Repository, KoinComponent{

    init {
        responseTreatment = get()
    }

    override suspend fun getMovie(movieId: Int): GetResponseApi {
        return  responseTreatment.getResponseApiMovie(movieId)
    }


    override suspend fun getSimilar(movieId: Int): GetResponseApi {
        return responseTreatment.getResponseApiSimilarMovie(movieId)
    }

    override suspend fun getGenre(): GetResponseApi {
        return responseTreatment.getResponseApiGenre()
    }
}