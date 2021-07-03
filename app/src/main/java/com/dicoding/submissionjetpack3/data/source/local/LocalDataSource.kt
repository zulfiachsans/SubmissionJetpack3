package com.dicoding.submissionjetpack3.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.submissionjetpack3.data.source.local.entity.MovieEntity
import com.dicoding.submissionjetpack3.data.source.local.entity.TvEntity
import com.dicoding.submissionjetpack3.data.source.local.room.AllDao
import com.dicoding.submissionjetpack3.utils.SortUtils
import com.dicoding.submissionjetpack3.utils.SortUtils.MOVIE_ENTITIES
import com.dicoding.submissionjetpack3.utils.SortUtils.TV_SHOW_ENTITIES

class LocalDataSource(private val mAllDao: AllDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(allDao: AllDao): LocalDataSource =
                INSTANCE ?: LocalDataSource(allDao)
    }

    fun getAllMovies(sort: String): DataSource.Factory<Int, MovieEntity> = mAllDao.getMovies(SortUtils.getSortedQuery(sort, MOVIE_ENTITIES))

    fun getMovieById(id: Int): LiveData<MovieEntity> = mAllDao.getMovieById(id)

    fun getFavMovies(): DataSource.Factory<Int, MovieEntity> = mAllDao.getFavMovies()

    fun getAllTvShows(sort: String): DataSource.Factory<Int, TvEntity> = mAllDao.getTvShows(SortUtils.getSortedQuery(sort, TV_SHOW_ENTITIES))

    fun getTvShowById(id: Int): LiveData<TvEntity> = mAllDao.getTvShowById(id)

    fun getFavTvShows(): DataSource.Factory<Int, TvEntity> = mAllDao.getFavTvShows()

    fun insertMovies(movies: List<MovieEntity>) = mAllDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        mAllDao.updateMovie(movie)
    }

    fun updateMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        mAllDao.updateMovie(movie)
    }

    fun insertTvShows(tvs: List<TvEntity>) = mAllDao.insertTvShows(tvs)

    fun setFavoriteTvShow(tv: TvEntity, newState: Boolean) {
        tv.isFav = newState
        mAllDao.updateTvShow(tv)
    }

    fun updateTvShow(tv: TvEntity, newState: Boolean) {
        tv.isFav = newState
        mAllDao.updateTvShow(tv)
    }
}