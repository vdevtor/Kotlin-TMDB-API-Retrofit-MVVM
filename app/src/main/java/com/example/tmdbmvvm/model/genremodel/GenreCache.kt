package com.example.tmdbmvvm.model.genremodel

object GenreCache {
    var genres = listOf<Genre>()

    fun cacheGenres(genres: List<Genre>) {
        this.genres = genres
    }
}