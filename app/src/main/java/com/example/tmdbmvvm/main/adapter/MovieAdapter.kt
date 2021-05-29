package com.example.tmdbmvvm.main.adapter

import com.example.tmdbmvvm.model.moviemodel.MovieDetail
import com.example.tmdbmvvm.databinding.ActivityHomeBinding
import com.example.tmdbmvvm.utils.formatK
import com.example.tmdbmvvm.utils.load

class MovieAdapter {

    fun bind(movie: MovieDetail, binding: ActivityHomeBinding) = with(binding) {
        movie.getFullPosterPath()?.let { mainMovieCover.load(it) }
        titleMainMovie.text = movie.title
        amountOfLikes.text = formatK(movie.voteCount)
        voteAverage.text = movie.voteAverage.toString()

    }
}