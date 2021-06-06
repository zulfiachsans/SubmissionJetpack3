package com.dicoding.submissionjetpack1.ui.movies

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest{
    private lateinit var movieViewModel: MoviesViewModel

    @Before
    fun setAll(){
        movieViewModel = MoviesViewModel()
    }
    @Test
    fun getDataMovies(){
        val movies = movieViewModel.getMovies()
        assertNotNull(movies)
        assertEquals(18,movies.size)
    }
}