package com.example.tmdbmvvm.model.moviemodel

import android.os.Parcelable
import com.example.tmdbmvvm.model.GeneroModel.Genre
import com.example.tmdbmvvm.utils.Constants.Api.SMALL_IMAGE_URL
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieDetail(
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        val genres: List<Genre>,
        val homepage: String,
        val id: Int,
        @SerializedName("imdb_id")
        val imdbId: String,
        @SerializedName("original_language:")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        @SerializedName("poster_path")
        var posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        val status: String,
        val title: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
) : Parcelable {

    fun getFullBackdropPath() =
            if (backdropPath.isNullOrBlank()) null else SMALL_IMAGE_URL + backdropPath

    fun getFullPosterPath() =
            if (posterPath.isNullOrBlank()) null else SMALL_IMAGE_URL + posterPath
}
