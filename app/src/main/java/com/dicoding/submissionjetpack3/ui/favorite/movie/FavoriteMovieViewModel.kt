package com.dicoding.submissionjetpack3.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.dicoding.submissionjetpack3.data.source.AllCatalougeRepo
import com.dicoding.submissionjetpack3.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val repo: AllCatalougeRepo) : ViewModel() {
    fun getFavMovies() = repo.getFavoriteMovies()

    fun setFavMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFav
        repo.setFavoriteMovie(movieEntity, newState)
    }
}