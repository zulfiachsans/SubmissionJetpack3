package com.dicoding.submissionjetpack1.ui.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.submissionjetpack1.utils.DataDummy

class TvViewModel : ViewModel(){
    fun getTv() = DataDummy.generateDummyTvShows()
}