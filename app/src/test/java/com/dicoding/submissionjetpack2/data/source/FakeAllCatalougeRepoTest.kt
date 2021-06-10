package com.dicoding.submissionjetpack2.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.submissionjetpack2.data.source.local.DetailEntity
import com.dicoding.submissionjetpack2.data.source.local.MovieEntity
import com.dicoding.submissionjetpack2.data.source.local.TvEntity
import com.dicoding.submissionjetpack2.data.source.remote.RemoteAllDataSource
import com.dicoding.submissionjetpack2.data.source.remote.response.movie.DetailMovieResponse
import com.dicoding.submissionjetpack2.data.source.remote.response.movie.Movie
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class FakeAllCatalougeRepoTest(private val remoteAllDataSource: RemoteAllDataSource) :
    AllCatalougeDataSource {
    override fun getMovies(): LiveData<List<MovieEntity>> {
        val movieResult = MutableLiveData<List<MovieEntity>>()

        remoteAllDataSource.getMovies(object : RemoteAllDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>?) {
                val movieList = ArrayList<MovieEntity>()
                if (movies != null) {
                    for (response in movies) {
                        with(response) {
                            val movie = MovieEntity(id, title, posterPath, voteAverage)
                            movieList.add(movie)
                        }
                    }
                    movieResult.postValue(movieList)
                }
            }
        })
        return movieResult
    }

    override fun getDetailMovie(movieId: String): LiveData<DetailEntity> {
        val movieDetailResult = MutableLiveData<DetailEntity>()
        remoteAllDataSource.getDetailMovie(object : RemoteAllDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieLoaded(movieDetail: DetailMovieResponse?) {
                if (movieDetail != null) {
                    with(movieDetail) {
                        val listGenre = ArrayList<String>()
                        for (genre in genres) {
                            listGenre.add(genre.name)
                        }
                        val detailMovie = DetailEntity (
                            backdropPath = backdropPath,
                            genres = listGenre,
                            id = id,
                            overview = overview,
                            posterPath = posterPath,
                            releaseDate = releaseDate,
                            runtime = runtime,
                            title = title,
                            voteAverage = voteAverage
                        )
                        movieDetailResult.postValue(detailMovie)
                    }
                }
            }
        },movieId)
        return movieDetailResult
    }

    override fun getTvShows(): LiveData<List<TvEntity>> {
        TODO("Not yet implemented")
    }

    override fun getDetailTvShow(tvShowId: String): LiveData<DetailEntity> {
        TODO("Not yet implemented")
    }
}