package com.dicoding.submissionjetpack3.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.submissionjetpack3.data.source.local.entity.MovieEntity
import com.dicoding.submissionjetpack3.data.source.local.entity.TvEntity
import com.dicoding.submissionjetpack3.vo.Resource

interface AllCatalougeDataSource {
    fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvEntity>>>

    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvEntity>>

    fun setFavoriteTvShow(tv: TvEntity, state: Boolean)
}