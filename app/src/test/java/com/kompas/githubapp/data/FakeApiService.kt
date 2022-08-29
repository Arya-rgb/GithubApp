package com.kompas.githubapp.data

import com.kompas.githubapp.data.remote.response.DataDetailResult
import com.kompas.githubapp.data.remote.response.RepoResult
import com.kompas.githubapp.data.remote.response.SearchResult
import com.kompas.githubapp.data.remote.response.UserResult
import com.kompas.githubapp.data.remote.retrofit.ApiService
import com.kompas.githubapp.utils.DataDummy

class FakeApiService : ApiService {

    private val dummyResponse = DataDummy.generateSearchUser()
    private val dummyResponseUser = DataDummy.generateDataDummyUserResult()

    override suspend fun getUserList(token: String): List<UserResult> {
        return dummyResponseUser
    }

    override suspend fun getDataUser(token: String, name: String): DataDetailResult {
        TODO("Not yet implemented")
    }

    override suspend fun getUserRepository(token: String, user: String): List<RepoResult> {
        TODO("Not yet implemented")
    }

    override suspend fun searchUser(token: String, q: String): SearchResult {
        return dummyResponse
    }

}