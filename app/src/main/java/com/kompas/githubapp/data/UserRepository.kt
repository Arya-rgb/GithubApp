package com.kompas.githubapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.kompas.githubapp.data.local.entity.UserEntity
import com.kompas.githubapp.data.local.room.UserDao
import com.kompas.githubapp.data.remote.retrofit.ApiService
import java.lang.Exception

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {

    fun getUserList(apiKey: String): LiveData<Result<List<UserEntity>>> = liveData {

        emit(Result.Loading)

        try {
            val response = apiService.getUserList("Bearer $apiKey")
            userDao.insertUser(UserEntity(response.login, response.id, response.avatar_url))
        } catch (e: Exception) {
            Log.d("userlist", "prepopulateddata: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<UserEntity>>> =
            userDao.getUser().map { Result.Success(it) }
        emitSource(localData)

    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userDao: UserDao
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userDao)
            }.also { instance = it }
    }

}