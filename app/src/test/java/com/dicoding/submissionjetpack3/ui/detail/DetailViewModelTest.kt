package com.dicoding.submissionjetpack3.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.submissionjetpack3.data.source.AllCatalougeRepo
import com.dicoding.submissionjetpack3.data.source.AllCatalougeRepoTest
import com.dicoding.submissionjetpack3.data.source.local.entity.MovieEntity
import com.dicoding.submissionjetpack3.data.source.local.entity.TvEntity
import com.dicoding.submissionjetpack3.ui.detail.DetailViewModel.Companion.TV_SHOW
import com.dicoding.submissionjetpack3.utils.DataDummy
import com.dicoding.submissionjetpack3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataDummy.getDetailMovie()
    private val dummyMovieId = dummyMovie.id

    private val dummyTvShow = DataDummy.getDetailTvShow()
    private val dummyTvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var allCatalougeRepo: AllCatalougeRepo

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvObserver: Observer<Resource<TvEntity>>

    // Get Data Movie Testing
    @Before
    fun setUpMovie() {
        viewModel = DetailViewModel(allCatalougeRepo)
    }

    @Test
    fun getMovieDetail() {
        val dummyDetailMovie = Resource.success(DataDummy.getDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(allCatalougeRepo.getDetailMovie(dummyMovieId)).thenReturn(movie)

        viewModel.setAll(dummyMovieId.toString(), DetailViewModel.MOVIE)
        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.getDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(allCatalougeRepo.getDetailMovie(dummyMovieId)).thenReturn(movie)

        viewModel.setAll(dummyMovieId.toString(), DetailViewModel.MOVIE)
        viewModel.setFavoriteMovie()
        verify(allCatalougeRepo).setFavoriteMovie(movie.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(movieObserver)
    }

    // Get Data TV Show Testing
    @Before
    fun setupTvShow() {
        viewModel = DetailViewModel(allCatalougeRepo)
    }

    @Test
    fun getTvShowDetail() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(allCatalougeRepo.getDetailTvShow(dummyTvShowId)).thenReturn(tvShow)

        viewModel.setAll(dummyTvShowId.toString(), TV_SHOW)
        viewModel.getDetailTvShow().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(allCatalougeRepo.getDetailTvShow(dummyTvShowId)).thenReturn(tvShow)

        viewModel.setAll(dummyTvShowId.toString(), TV_SHOW)
        viewModel.setFavoriteTvShow()
        verify(allCatalougeRepo).setFavoriteTvShow(tvShow.value!!.data as TvEntity, true)
        verifyNoMoreInteractions(tvObserver)
    }
}
