package com.example.tmdbmvvm.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmdbmvvm.data.repository.Repository_Imp
import com.example.tmdbmvvm.data.model.moviemodel.MovieDetail
import com.example.tmdbmvvm.data.model.similarmoviemodel.SimilarMoviesModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmvvm.data.api.GetResponseApi
import com.example.tmdbmvvm.data.model.GeneroModel.Generos
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository_Imp) : ViewModel() {

    val onResultSimilarMovies: MutableLiveData<SimilarMoviesModel> = MutableLiveData()
    val onResultMovieDetail: MutableLiveData<MovieDetail> = MutableLiveData()
    val onResultGenreList: MutableLiveData<Generos> = MutableLiveData()
    val onResultFailure: MutableLiveData<String> = MutableLiveData()

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            when (val response = repository.getMovie(movieId)) {
                is GetResponseApi.ResponseSucess -> {
                    onResultMovieDetail.postValue(response.data as MovieDetail)
                }
                is GetResponseApi.ResponseError -> {
                    onResultFailure.postValue(response.message)
                }
            }
        }
    }

    fun getSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            when (val response = repository.getSimilar(movieId)) {
                is GetResponseApi.ResponseSucess -> {
                    onResultSimilarMovies.postValue(response.data as SimilarMoviesModel)
                }
                is GetResponseApi.ResponseError -> {
                    onResultFailure.postValue(response.message)
                }
            }
        }
    }

    fun getGenre(genre: Int) {
        viewModelScope.launch {
            when (val response = repository.getGenre(genre)) {
                is GetResponseApi.ResponseSucess -> {
                    onResultGenreList.postValue(response.data as Generos)
                }
                is GetResponseApi.ResponseError -> {
                    onResultFailure.postValue(response.message)
                }
            }
        }
    }
}

