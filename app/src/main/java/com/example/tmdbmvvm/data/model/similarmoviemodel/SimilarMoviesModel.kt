package com.example.tmdbmvvm.data.model.similarmoviemodel

import com.google.gson.annotations.SerializedName

data class SimilarMoviesModel(
        val page: Int,
        @SerializedName("results")
        val resultSimilarMovies: List<ResultSimilarMovies?>?,
        val total_pages: Int,
        val total_results: Int
)