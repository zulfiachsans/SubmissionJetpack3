package com.dicoding.submissionjetpack1.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.submissionjetpack1.model.AllEntity
import com.dicoding.submissionjetpack1.utils.DataDummy

class DetailViewModel : ViewModel(){
    companion object {
        const val TV_SHOWS = "tvShows"
        const val MOVIES = "movies"
    }

    private lateinit var all : AllEntity

    fun setAll(id: String, category: String) {
        when (category) {
            TV_SHOWS -> {
                for (tvShows in DataDummy.generateDummyTvShows()) {
                    if (tvShows.id == id) all = tvShows
                }
            }

            MOVIES -> {
                for (movies in DataDummy.generateDummyMovies()) {
                    if (movies.id == id) all = movies
                }
            }
        }
    }
    fun getAllDetail() = all
}