package com.dicoding.submissionjetpack3.ui.movies

import androidx.lifecycle.ViewModel
import com.dicoding.submissionjetpack3.data.source.AllCatalougeRepo

class MoviesViewModel(private val movieCatalogueRepo: AllCatalougeRepo) : ViewModel() {
    fun getMovies(sort: String) = movieCatalogueRepo.getMovies(sort)
}