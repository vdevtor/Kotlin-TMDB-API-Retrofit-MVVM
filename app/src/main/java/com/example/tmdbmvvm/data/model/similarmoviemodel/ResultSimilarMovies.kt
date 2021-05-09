package com.example.tmdbmvvm.data.model.similarmoviemodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultSimilarMovies(
        val adult: Boolean,
        val backdrop_path: String,
        val genre_ids: List<Int>,
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        var poster_path: String?,
        val release_date: String?,
        val title: String?,
        val video: Boolean,
        val vote_average: Float?,
        val vote_count: Int
): Parcelable