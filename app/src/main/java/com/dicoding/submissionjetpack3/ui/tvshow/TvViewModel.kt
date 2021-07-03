package com.dicoding.submissionjetpack3.ui.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.submissionjetpack3.data.source.AllCatalougeRepo

class TvViewModel(private val movieCatalogueRepo: AllCatalougeRepo) : ViewModel() {
    fun getTvShows(sort: String) = movieCatalogueRepo.getTvShows(sort)
}