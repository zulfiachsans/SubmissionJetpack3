package com.dicoding.submissionjetpack3.di

import android.content.Context
import com.dicoding.submissionjetpack3.data.source.AllCatalougeRepo
import com.dicoding.submissionjetpack3.data.source.local.LocalDataSource
import com.dicoding.submissionjetpack3.data.source.local.room.AllDatabase
import com.dicoding.submissionjetpack3.data.source.remote.RemoteAllDataSource
import com.dicoding.submissionjetpack3.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): AllCatalougeRepo {
        val database = AllDatabase.getInstance(context)

        val remoteDataSource = RemoteAllDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()
        return AllCatalougeRepo.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}