package com.kompas.githubapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.kompas.githubapp.data.local.entity.UserEntity
import com.kompas.githubapp.data.local.room.UserDao
import com.kompas.githubapp.data.remote.response.DataDetailResult
import com.kompas.githubapp.data.remote.response.RepoResult
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
            val userList = response.map {
                UserEntity(
                    it.login,
                    it.id,
                    it.avatar_url
                )
            }
            userDao.insertUser(userList)
        } catch (e: Exception) {
            Log.d("userlist", "prepopulateddata: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<UserEntity>>> =
            userDao.getUser().map { Result.Success(it) }
        emitSource(localData)

    }


    fun getRepoList(token: String, name: String) : LiveData<Result<List<RepoResult>>> = liveData {

        emit(Result.Loading)

        try {
            val response = apiService.getUserRepository("Bearer $token", name)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("UserRespository", "Getrepo ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }

    }


    fun getDataUser(token: String, name: String): LiveData<Result<DataDetailResult>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDataUser("Bearer $token", name)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("UserRepository", "getUserData: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
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