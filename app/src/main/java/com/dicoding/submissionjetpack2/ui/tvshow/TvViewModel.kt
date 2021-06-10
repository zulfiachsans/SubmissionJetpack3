package com.dicoding.submissionjetpack2.ui.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.submissionjetpack2.data.source.AllCatalougeRepo

class TvViewModel(private val allCatalougeRepo: AllCatalougeRepo) : ViewModel(){
    fun getTv() = allCatalougeRepo.getTvShows()
}