package com.example.tmdbmvvm.model.similarmoviemodel

import com.google.gson.annotations.SerializedName

data class SimilarMoviesModel(
        val page: Int,
        @SerializedName("results")
        val resultSimilarMovies: List<ResultSimilarMovies?>?,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)