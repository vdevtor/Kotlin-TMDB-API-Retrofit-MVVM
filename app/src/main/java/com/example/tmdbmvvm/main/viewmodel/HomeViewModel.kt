package com.example.tmdbmvvm.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmdbmvvm.repository.RepositoryImplement
import com.example.tmdbmvvm.model.moviemodel.MovieDetail
import androidx.lifecycle.viewModelScope
import com.example.tmdbmvvm.data.response.GetResponseApi
import com.example.tmdbmvvm.model.GeneroModel.GenerosList
import com.example.tmdbmvvm.model.similarmoviemodel.ResultSimilarMovies
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: RepositoryImplement) : ViewModel() {

    val onResultSimilarMovies: MutableLiveData<List<ResultSimilarMovies>> = MutableLiveData()
    val onResultMovieDetail: MutableLiveData<MovieDetail> = MutableLiveData()
    val onResultGenreList: MutableLiveData<GenerosList> = MutableLiveData()
    val onResultFailure: MutableLiveData<String> = MutableLiveData()

    init {
        getGenre()
    }

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
                    onResultSimilarMovies.postValue(response.data as List<ResultSimilarMovies>)
                }
                is GetResponseApi.ResponseError -> {
                    onResultFailure.postValue(response.message)
                }
            }
        }
    }

    fun getGenre() {
        viewModelScope.launch {
            when (val response = repository.getGenre()) {
                is GetResponseApi.ResponseSucess -> {
                    onResultGenreList.postValue(response.data as GenerosList)
                }
                is GetResponseApi.ResponseError -> {
                    onResultFailure.postValue(response.message)
                }
            }
        }
    }
}

