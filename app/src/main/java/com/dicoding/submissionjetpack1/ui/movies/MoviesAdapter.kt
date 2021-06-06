package com.dicoding.submissionjetpack1.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submissionjetpack1.databinding.ItemRowBinding
import com.dicoding.submissionjetpack1.model.AllEntity
import com.dicoding.submissionjetpack1.ui.detail.DetailActivity
import com.dicoding.submissionjetpack1.ui.detail.DetailViewModel.Companion.MOVIES

class MoviesAdapter(moviesFragment: MoviesFragment) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private var movies = ArrayList<AllEntity>()

    fun setMovies(movies: List<AllEntity>) {
        if (movies.isNullOrEmpty()) return
        this.movies.clear()
        this.movies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemRowBinding =
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemRowBinding)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }
    override fun getItemCount(): Int = movies.size
    inner class MovieViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: AllEntity) {
            with(binding) {
                tvTitle.text = movies.title

                Glide.with(itemView.context)
                    .load(movies.poster)
                    .into(imgItemPhoto)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TYPE,movies.id)
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, MOVIES)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}