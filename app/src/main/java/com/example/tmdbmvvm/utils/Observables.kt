package com.example.tmdbmvvm.utils

import com.example.tmdbmvvm.data.model.GeneroModel.Genre
import com.example.tmdbmvvm.data.model.similarmoviemodel.SimilarMoviesModel
import com.example.tmdbmvvm.main.adapter.MovieAdapter
interface Observables {
     fun setMovieObservables(movieAdapter: MovieAdapter)
     fun setSimilarListObservables()
     fun setRecyclerView(similarMovie: SimilarMoviesModel?)



}