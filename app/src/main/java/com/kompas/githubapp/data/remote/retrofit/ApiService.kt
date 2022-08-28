package com.kompas.githubapp.data.remote.retrofit

import com.kompas.githubapp.data.remote.response.DataDetailResult
import com.kompas.githubapp.data.remote.response.RepoResult
import com.kompas.githubapp.data.remote.response.SearchResult
import com.kompas.githubapp.data.remote.response.UserResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUserList(
        @Header("Authorization") token: String,
    ): List<UserResult>

    @GET("users/{name}")
    suspend fun getDataUser(
        @Header("Authorization") token: String,
        @Path("name") name: String
    ): DataDetailResult

    @GET("users/{user}/repos")
    suspend fun getUserRepository(
        @Header("Authorization") token: String,
        @Path("user") user: String
    ): List<RepoResult>

    @GET("search/users")
    suspend fun searchUser(
        @Header("Authorization") token: String,
        @Query("q") q: String
    ): SearchResult

}