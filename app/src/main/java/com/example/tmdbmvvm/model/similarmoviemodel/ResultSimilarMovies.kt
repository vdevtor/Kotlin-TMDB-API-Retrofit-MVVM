package com.example.tmdbmvvm.model.similarmoviemodel

import android.os.Parcelable
import com.example.tmdbmvvm.model.genremodel.Genre
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultSimilarMovies(
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        val id: Int,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        @SerializedName("poster_path")
        var posterPath: String?,
        @SerializedName("release_date")
        val releaseDate: String?,
        val title: String?,
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Float?,
        @SerializedName("vote_count")
        val voteCount: Int,
        val genres: List<Genre>
): Parcelable