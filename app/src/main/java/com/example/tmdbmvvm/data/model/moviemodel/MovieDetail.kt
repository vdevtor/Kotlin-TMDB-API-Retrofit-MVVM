package com.example.tmdbmvvm.data.model.moviemodel

import android.os.Parcelable
import com.example.tmdbmvvm.data.model.GeneroModel.Genre
import com.example.tmdbmvvm.utils.Constants.Api.SMALL_IMAGE_URL
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieDetail(
        val adult: Boolean,
        val backdrop_path: String,
        val genres: List<Genre>,
        val homepage: String,
        val id: Int,
        val imdb_id: String,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        @SerializedName("poster_path")
        var posterPath: String,
        val release_date: String,
        val status: String,
        val title: String,
        val vote_average: Double,
        val vote_count: Int
) : Parcelable {

    fun getFullBackdropPath() =
            if (backdrop_path.isNullOrBlank()) null else SMALL_IMAGE_URL + backdrop_path

    fun getFullPosterPath() =
            if (posterPath.isNullOrBlank()) null else SMALL_IMAGE_URL + posterPath
}
