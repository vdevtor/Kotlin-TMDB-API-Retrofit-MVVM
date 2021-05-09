package com.example.tmdbmvvm.main.adapter

import com.example.tmdbmvvm.data.model.moviemodel.MovieDetail
import com.example.tmdbmvvm.databinding.ActivityHomeBinding
import com.example.tmdbmvvm.utils.formatK
import com.example.tmdbmvvm.utils.load

class MovieAdapter() {

    fun bind(movie: MovieDetail, binding: ActivityHomeBinding) = with(binding) {
        movie.getFullPosterPath()?.let { mainMovieCover.load(it) }
        titleMainMovie.text = movie.title
        amountOfLikes.text = formatK(movie.vote_count)
        voteAverage.text = movie.vote_average.toString()

    }
}