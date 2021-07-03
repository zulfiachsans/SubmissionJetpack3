package com.dicoding.submissionjetpack3.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.submissionjetpack3.data.source.local.LocalDataSource
import com.dicoding.submissionjetpack3.data.source.local.entity.MovieEntity
import com.dicoding.submissionjetpack3.data.source.local.entity.TvEntity
import com.dicoding.submissionjetpack3.data.source.remote.ApiResponse
import com.dicoding.submissionjetpack3.data.source.remote.RemoteAllDataSource
import com.dicoding.submissionjetpack3.data.source.remote.response.movie.DetailMovieResponse
import com.dicoding.submissionjetpack3.data.source.remote.response.movie.Movie
import com.dicoding.submissionjetpack3.data.source.remote.response.tv.DetailTvShowsResponse
import com.dicoding.submissionjetpack3.data.source.remote.response.tv.TvShows
import com.dicoding.submissionjetpack3.utils.AppExecutors
import com.dicoding.submissionjetpack3.vo.Resource

class FakeAllCatalougeRepoTest constructor(
    private val remoteAllDataSource: RemoteAllDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : AllCatalougeDataSource {

    override fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteAllDataSource.getMovies()

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        id = response.id,
                        backdropPath = response.backdropPath,
                        genres = "",
                        overview = response.overview,
                        posterPath = response.posterPath,
                        releaseDate = response.releaseDate,
                        runtime = 0,
                        title = response.title,
                        voteAverage = response.voteAverage,
                        isFav = false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<MovieEntity> = localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null && data.runtime == 0 && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteAllDataSource.getDetailMovie(movieId.toString())

            override fun saveCallResult(data: DetailMovieResponse) {
                val genres = StringBuilder().append("")

                for (i in data.genres.indices) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                val movie = MovieEntity(
                    id = data.id,
                    backdropPath = data.backdropPath,
                    genres = genres.toString(),
                    overview = data.overview,
                    posterPath = data.posterPath,
                    releaseDate = data.releaseDate,
                    runtime = data.runtime,
                    title = data.title,
                    voteAverage = data.voteAverage,
                    isFav = false
                )
                localDataSource.updateMovie(movie, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        localDataSource.setFavoriteMovie(movie, state)
    }

    override fun getTvShows(sort: String): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, List<TvShows>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllTvShows(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShows>>> =
                remoteAllDataSource.getTvShows()

            override fun saveCallResult(data: List<TvShows>) {
                val movieList = ArrayList<TvEntity>()
                for (response in data) {
                    val movie = TvEntity(
                        id = response.id,
                        backdropPath = response.backdropPath,
                        genres = "",
                        overview = response.overview,
                        posterPath = response.posterPath,
                        releaseDate = response.firstAirDate,
                        runtime = 0,
                        name = response.name,
                        voteAverage = response.voteAverage,
                        isFav = false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertTvShows(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, DetailTvShowsResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<TvEntity> = localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvEntity?): Boolean =
                data != null && data.runtime == 0 && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<DetailTvShowsResponse>> =
                remoteAllDataSource.getDetailTvShow(tvShowId.toString())

            override fun saveCallResult(data: DetailTvShowsResponse) {
                val genres = StringBuilder().append("")

                for (i in data.genres.indices) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                val tvShow = TvEntity(
                    id = data.id,
                    backdropPath = data.backdropPath,
                    genres = genres.toString(),
                    overview = data.overview,
                    posterPath = data.posterPath,
                    releaseDate = data.firstAirDate,
                    runtime = data.episodeRunTime.first(),
                    name = data.name,
                    voteAverage = data.voteAverage,
                    isFav = false
                )
                localDataSource.updateTvShow(tvShow, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tv: TvEntity, state: Boolean) {
        localDataSource.setFavoriteTvShow(tv, state)
    }
}