package com.dicoding.submissionjetpack3.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.submissionjetpack3.data.source.AllCatalougeRepo
import com.dicoding.submissionjetpack3.data.source.local.entity.TvEntity

class FavoriteTvShowViewModel(private val repo: AllCatalougeRepo) : ViewModel() {
    fun getFavTvShows() = repo.getFavoriteTvShows()

    fun setFavTvShow(tvEntity: TvEntity) {
        val newState = !tvEntity.isFav
        repo.setFavoriteTvShow(tvEntity, newState)
    }
}