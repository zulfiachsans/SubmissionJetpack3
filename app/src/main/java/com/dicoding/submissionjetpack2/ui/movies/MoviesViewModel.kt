package com.dicoding.submissionjetpack2.ui.movies

import androidx.lifecycle.ViewModel
import com.dicoding.submissionjetpack2.data.source.AllCatalougeRepo
import com.dicoding.submissionjetpack2.utils.DataDummy

class MoviesViewModel(private val allCatalougeRepo: AllCatalougeRepo) : ViewModel() {
    fun getMovies() = allCatalougeRepo.getMovies()
}