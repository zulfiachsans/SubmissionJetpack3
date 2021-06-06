package com.dicoding.submissionjetpack1.ui.movies

import androidx.lifecycle.ViewModel
import com.dicoding.submissionjetpack1.utils.DataDummy

class MoviesViewModel: ViewModel() {
    fun getMovies() = DataDummy.generateDummyMovies()
}