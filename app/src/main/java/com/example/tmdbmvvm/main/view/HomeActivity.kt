package com.example.tmdbmvvm.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbmvvm.data.business.OnclickButton
import com.example.tmdbmvvm.model.similarmoviemodel.ResultSimilarMovies
import com.example.tmdbmvvm.databinding.ActivityHomeBinding
import com.example.tmdbmvvm.main.adapter.MovieAdapter
import com.example.tmdbmvvm.main.adapter.SimilarMoviesAdapter
import com.example.tmdbmvvm.utils.Constants.Companion.MOVIE_ID
import com.example.tmdbmvvm.utils.Observables
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity(), Observables {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieAdapter = get<MovieAdapter>()
        val onClickButton = get<OnclickButton>()

        viewModel.getMovie(MOVIE_ID)
        viewModel.getSimilarMovies(MOVIE_ID)
        setObservables(movieAdapter)

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
        viewModel.onResultFailure.observe(this,{
            if (!it.isNullOrBlank()){
                Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun setRecyclerView(similarMovieList: List<ResultSimilarMovies>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = SimilarMoviesAdapter(similarMovieList)
        }
    }

    override fun setObservables(movieAdapter: MovieAdapter) {

            setMovieObservables(movieAdapter)
            setSimilarListObservables()
    }
}