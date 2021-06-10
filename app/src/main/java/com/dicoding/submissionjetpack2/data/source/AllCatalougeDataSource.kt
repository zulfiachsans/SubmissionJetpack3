package com.dicoding.submissionjetpack2.data.source

import androidx.lifecycle.LiveData
import com.dicoding.submissionjetpack2.data.source.local.DetailEntity
import com.dicoding.submissionjetpack2.data.source.local.MovieEntity
import com.dicoding.submissionjetpack2.data.source.local.TvEntity

interface AllCatalougeDataSource {
    fun getMovies(): LiveData<List<MovieEntity>>
    fun getDetailMovie(movieId: String): LiveData<DetailEntity>
    fun getTvShows(): LiveData<List<TvEntity>>
    fun getDetailTvShow(tvShowId: String): LiveData<DetailEntity>
}