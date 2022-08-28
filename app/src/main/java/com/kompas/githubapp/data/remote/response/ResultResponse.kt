package com.kompas.githubapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResult(

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("avatar_url")
    val avatar_url: String,
)


data class DataDetailResult(

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("bio")
    val bio: String,

    @field:SerializedName("twitter_username")
    val twitter_username: String,

)