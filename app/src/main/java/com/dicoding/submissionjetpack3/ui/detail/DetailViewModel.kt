package com.dicoding.submissionjetpack3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submissionjetpack3.data.source.AllCatalougeRepo
import com.dicoding.submissionjetpack3.data.source.local.entity.MovieEntity
import com.dicoding.submissionjetpack3.data.source.local.entity.TvEntity
import com.dicoding.submissionjetpack3.vo.Resource

class DetailViewModel(private val allCatalougeRepo: AllCatalougeRepo) : ViewModel() {
    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tvShow"
    }

    private lateinit var detailTv: LiveData<Resource<TvEntity>>
    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>

    fun setAll(id: String, category: String) {
        when (category) {
            TV_SHOW -> {
                detailTv = allCatalougeRepo.getDetailTvShow(id.toInt())
            }

            MOVIE -> {
                detailMovie = allCatalougeRepo.getDetailMovie(id.toInt())
            }
        }
    }

    fun setFavoriteMovie() {
        val resource = detailMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.isFav
            allCatalougeRepo.setFavoriteMovie(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTv.value
        if (resource?.data != null) {
            val newState = !resource.data.isFav
            allCatalougeRepo.setFavoriteTvShow(resource.data, newState)
        }
    }

    fun getDetailTvShow() = detailTv
    fun getDetailMovie() = detailMovie

}