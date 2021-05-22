package com.example.tmdbmvvm.utils

import com.example.tmdbmvvm.model.similarmoviemodel.ResultSimilarMovies
import com.example.tmdbmvvm.main.adapter.MovieAdapter
interface Observables {

     fun setMovieObservables(movieAdapter: MovieAdapter)
     fun setSimilarListObservables()
     fun setRecyclerView(similarMovieList: List<ResultSimilarMovies>)

}