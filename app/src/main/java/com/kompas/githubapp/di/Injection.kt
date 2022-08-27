package com.kompas.githubapp.di

import android.content.Context
import com.kompas.githubapp.data.UserRepository
import com.kompas.githubapp.data.local.room.UserDatabase
import com.kompas.githubapp.data.remote.retrofit.ApiConfig

object Injection {

    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val database = UserDatabase.getInstance(context)
        val dao = database.userDao()
        return UserRepository.getInstance(apiService, dao)
    }

}