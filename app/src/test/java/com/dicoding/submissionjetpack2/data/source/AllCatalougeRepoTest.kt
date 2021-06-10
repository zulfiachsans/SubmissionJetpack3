package com.dicoding.submissionjetpack2.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.submissionjetpack2.data.source.remote.RemoteAllDataSource
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class AllCatalougeRepoTest {
    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule()
    private val remoteData = mock(RemoteAllDataSource::class.java)
    private val allCatalougeRepo = FakeAllCatalougeRepoTest(remoteData)

    @Test
    fun getMovies() {
    }

    @Test
    fun getDetailMovie() {
    }

    @Test
    fun getTvShows() {
    }

    @Test
    fun getDetailTvShow() {
    }
}