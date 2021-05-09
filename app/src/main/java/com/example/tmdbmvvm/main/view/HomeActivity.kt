package com.example.tmdbmvvm.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbmvvm.data.business.OnclickButton
import com.example.tmdbmvvm.data.model.similarmoviemodel.SimilarMoviesModel
import com.example.tmdbmvvm.databinding.ActivityHomeBinding
import com.example.tmdbmvvm.main.adapter.MovieAdapter
import com.example.tmdbmvvm.main.adapter.SimilarMoviesAdapter
import com.example.tmdbmvvm.main.viewmodel.HomeViewModel
import com.example.tmdbmvvm.utils.Observables
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity() : AppCompatActivity(), Observables {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieAdapter = get<MovieAdapter>()
        val onClickButton = get<OnclickButton>()

        viewModel.getMovie(464052)
        viewModel.getSimilarMovies(464052)
        setMovieObservables(movieAdapter)
        setSimilarListObservables()

        binding.mainHeart.setOnClickListener {
            onClickButton.onHeartClick(binding)
        }

        binding.secundaryHeart.setOnClickListener {
            onClickButton.onHeartClick(binding)
        }
    }

    override fun setMovieObservables(movieAdapter: MovieAdapter) {
        viewModel.onResultMovieDetail.observe(this, {
            it.let {
                movieAdapter.bind(it, binding)
            }
        })
    }

    override fun setSimilarListObservables() {
        viewModel.onResultSimilarMovies.observe(this) {
            setRecyclerView(it)
        }
    }

    override fun setRecyclerView(similarMovie: SimilarMoviesModel?) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            if (similarMovie != null) {

                adapter = similarMovie.resultSimilarMovies?.let { SimilarMoviesAdapter(it) }
            }
        }
    }

}