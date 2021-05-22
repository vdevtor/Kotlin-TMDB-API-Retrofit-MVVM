package com.example.tmdbmvvm.model.GeneroModel

object GenreCache {
    var genres = listOf<Genre>()

    fun cacheGenres(genres: List<Genre>) {
        this.genres = genres
    }
}