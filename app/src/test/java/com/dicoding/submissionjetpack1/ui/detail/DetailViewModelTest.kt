package com.dicoding.submissionjetpack1.ui.detail

import com.dicoding.submissionjetpack1.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovies = DataDummy.generateDummyMovies()[0]
    private val dummyId = dummyMovies.id

    private val dummyTv = DataDummy.generateDummyTvShows()[0]
    private val dummyTvId = dummyTv.id

    //Get data Movie Testing
    @Before
    fun setMovie() {
        viewModel = DetailViewModel()
        viewModel.setAll(dummyId, "movies")
    }

    @Test
    fun getDetailMovie() {
        viewModel.setAll(dummyMovies.id, "movies")
        val movies = viewModel.getAllDetail()
        assertNotNull(movies)
        assertEquals(dummyMovies.id, movies.id)
        assertEquals(dummyMovies.title, movies.title)
        assertEquals(dummyMovies.description, movies.description)
        assertEquals(dummyMovies.genre, movies.genre)
        assertEquals(dummyMovies.realeaseYear, movies.realeaseYear)
        assertEquals(dummyMovies.duration, movies.duration)
        assertEquals(dummyMovies.poster, movies.poster)
        assertEquals(dummyMovies.background, movies.background)
    }

    //Get Tv data Testing
    @Before
    fun setTv() {
        viewModel = DetailViewModel()
        viewModel.setAll(dummyTvId, "tvShows")
    }
    @Test
    fun getTvDetail() {
        viewModel.setAll(dummyTv.id, "tvShows")
        val tvShow = viewModel.getAllDetail()
        assertNotNull(tvShow)
        assertEquals(dummyTv.id, tvShow.id)
        assertEquals(dummyTv.title, tvShow.title)
        assertEquals(dummyTv.description, tvShow.description)
        assertEquals(dummyTv.genre, tvShow.genre)
        assertEquals(dummyTv.realeaseYear, tvShow.realeaseYear)
        assertEquals(dummyTv.duration, tvShow.duration)
        assertEquals(dummyTv.poster, tvShow.poster)
        assertEquals(dummyTv.background, tvShow.background)
    }
}
