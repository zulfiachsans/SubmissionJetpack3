package com.dicoding.submissionjetpack3.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.submissionjetpack3.data.source.AllCatalougeRepo
import com.dicoding.submissionjetpack3.di.Injection
import com.dicoding.submissionjetpack3.ui.detail.DetailViewModel
import com.dicoding.submissionjetpack3.ui.favorite.movie.FavoriteMovieViewModel
import com.dicoding.submissionjetpack3.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.dicoding.submissionjetpack3.ui.movies.MoviesViewModel
import com.dicoding.submissionjetpack3.ui.tvshow.TvViewModel

class ViewModelFactory private constructor(private val movieCatalogueRepo: AllCatalougeRepo) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(movieCatalogueRepo) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(movieCatalogueRepo) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieCatalogueRepo) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(movieCatalogueRepo) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                FavoriteTvShowViewModel(movieCatalogueRepo) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}