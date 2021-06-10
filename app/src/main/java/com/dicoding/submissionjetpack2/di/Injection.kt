package com.dicoding.submissionjetpack2.di

import android.content.Context
import com.dicoding.submissionjetpack2.data.source.AllCatalougeRepo
import com.dicoding.submissionjetpack2.data.source.remote.RemoteAllDataSource

object Injection {
    fun provideRepository(context: Context): AllCatalougeRepo {
        val remoteDataSource = RemoteAllDataSource.getInstance()
        return AllCatalougeRepo.getInstance(remoteDataSource)
    }
}