package com.dicoding.submissionjetpack2.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submissionjetpack2.data.source.AllCatalougeRepo
import com.dicoding.submissionjetpack2.data.source.local.DetailEntity

class DetailViewModel(private val allCatalougeRepo: AllCatalougeRepo) : ViewModel(){
    companion object {
        const val TV_SHOWS = "tvShows"
        const val MOVIES = "movies"
    }

    private lateinit var all : LiveData<DetailEntity>

    fun setAll(id: String, category: String) {
        when (category) {
            TV_SHOWS -> {
               all = allCatalougeRepo.getDetailTvShow(id)
            }

            MOVIES -> {
               all = allCatalougeRepo.getDetailMovie(id)
            }
        }
    }
    fun getAllDetail() = all
}