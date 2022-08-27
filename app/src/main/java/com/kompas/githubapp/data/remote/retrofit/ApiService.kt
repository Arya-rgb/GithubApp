package com.kompas.githubapp.data.remote.retrofit

import com.kompas.githubapp.data.remote.response.UserResult
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("users")
    suspend fun getUserList(
        @Header("Authorization") token: String,
    ): List<UserResult>

}