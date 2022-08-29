package com.kompas.githubapp.utils

import com.kompas.githubapp.data.local.entity.UserEntity
import com.kompas.githubapp.data.remote.response.RepoResult
import com.kompas.githubapp.data.remote.response.SearchResult
import com.kompas.githubapp.data.remote.response.UserResult

object DataDummy {

    fun generateDummyUserEntity(): List<UserEntity> {

        val userList = ArrayList<UserEntity>()
        for (i in 0..10) {
            val user = UserEntity(
                "Arya-rgb",
                101340,
                "https://avatars.githubusercontent.com/u/101340?v=4"
            )
            userList.add(user)
        }
        return userList
    }

    fun generateDummyRepo(): List<RepoResult> {

        val repoList = ArrayList<RepoResult>()
        for (i in 0..10) {
            val repo = RepoResult(
                "jetpack Compose",
                "implementing jetpack compose",
                "2022-02-11T12:25:04Z",
            "5"
            )
            repoList.add(repo)
        }
        return repoList
    }

    fun generateDataDummyUserResult(): List<UserResult> {

        val userList = ArrayList<UserResult>()
        for (i in 0..10) {
            val user = UserResult(
                "Arya-rgb",
                101340,
                "https://avatars.githubusercontent.com/u/101340?v=4"
            )
            userList.add(user)
        }
        return userList
    }

    fun generateSearchUser(): SearchResult {
        val userList = ArrayList<UserResult>()
        for (i in 0..10) {
            val user = UserResult(
                "Arya-rgb",
                101340,
                "https://avatars.githubusercontent.com/u/101340?v=4"
            )
            userList.add(user)
        }
        return SearchResult(userList.size, false, userList)
    }

}