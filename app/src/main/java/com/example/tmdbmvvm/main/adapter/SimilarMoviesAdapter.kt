package com.example.tmdbmvvm.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbmvvm.model.similarmoviemodel.ResultSimilarMovies
import com.example.tmdbmvvm.databinding.SimilarListItemBinding
import com.example.tmdbmvvm.utils.load


class SimilarMoviesAdapter(
        private var similarList: List<ResultSimilarMovies?>
) : RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: SimilarListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(similar: ResultSimilarMovies) = with(binding) {
            similarMovieTitleList.text = similar.title
            similar.posterPath?.let { similarMoviePoster.load(it) }
            movieYearList.text = similar.releaseDate?.substring(0, 4)
            movieCategoryList.text = similar.genres.joinToString(separator =", "){ it.name}

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMoviesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SimilarListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarMoviesAdapter.ViewHolder, position: Int) {
        similarList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return similarList.size
    }
}