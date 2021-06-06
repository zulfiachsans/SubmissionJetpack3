package com.dicoding.submissionjetpack1.ui.tvshow

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvViewModelTest{
    private lateinit var tvViewModel: TvViewModel

    @Before
    fun setAll(){
        tvViewModel = TvViewModel()
    }
    @Test
    fun getDataTv(){
        val tv = tvViewModel.getTv()
        assertNotNull(tv)
        assertEquals(12,tv.size)
    }
}