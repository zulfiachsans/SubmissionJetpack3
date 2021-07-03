package com.dicoding.submissionjetpack3.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.submissionjetpack3.BuildConfig.API_KEY
import com.dicoding.submissionjetpack3.data.source.remote.response.movie.Movie
import com.dicoding.submissionjetpack3.data.source.remote.response.movie.DetailMovieResponse
import com.dicoding.submissionjetpack3.data.source.remote.response.movie.MovieResponse
import com.dicoding.submissionjetpack3.data.source.remote.response.tv.TvShows
import com.dicoding.submissionjetpack3.data.source.remote.response.tv.DetailTvShowsResponse
import com.dicoding.submissionjetpack3.data.source.remote.response.tv.TvShowsResponse
import com.dicoding.submissionjetpack3.network.ApiConfig
import com.dicoding.submissionjetpack3.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteAllDataSource {
    companion object {
        @Volatile
        private var instance: RemoteAllDataSource? = null

        fun getInstance(): RemoteAllDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteAllDataSource()
                }
    }

    fun getMovies(): LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<Movie>>>()
        val client = ApiConfig.getApiService().getMovies(API_KEY)

        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                resultMovies.value = ApiResponse.success(response.body()?.results as List<Movie>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultMovies
    }

    fun getDetailMovie(movieId: String): LiveData<ApiResponse<DetailMovieResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        val client = ApiConfig.getApiService().getMovieDetail(movieId, API_KEY)

        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(call: Call<DetailMovieResponse>, movieResponse: Response<DetailMovieResponse>) {
                resultDetailMovie.value = ApiResponse.success(movieResponse.body() as DetailMovieResponse)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovieDetail onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultDetailMovie
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvShows>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<TvShows>>>()
        val client = ApiConfig.getApiService().getTvShows(API_KEY)

        client.enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(call: Call<TvShowsResponse>, response: Response<TvShowsResponse>) {
                resultTvShows.value = ApiResponse.success(response.body()?.results as List<TvShows>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvShows onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultTvShows
    }

    fun getDetailTvShow(tvShowId: String): LiveData<ApiResponse<DetailTvShowsResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailTvShow = MutableLiveData<ApiResponse<DetailTvShowsResponse>>()
        val client = ApiConfig.getApiService().getTvShowDetail(tvShowId, API_KEY)

        client.enqueue(object : Callback<DetailTvShowsResponse> {
            override fun onResponse(call: Call<DetailTvShowsResponse>, tvShowsResponse: Response<DetailTvShowsResponse>) {
                resultDetailTvShow.value = ApiResponse.success(tvShowsResponse.body() as DetailTvShowsResponse)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailTvShowsResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailTvShow onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultDetailTvShow
    }
}