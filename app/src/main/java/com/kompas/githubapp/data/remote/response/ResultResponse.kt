package com.kompas.githubapp.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SearchResult(
    @field:SerializedName("total_count")
    val total_count: Int,

    @field:SerializedName("incomplete_results")
    val incomplete_results: Boolean,

    @field:SerializedName("items")
    val items: List<UserResult>,

)

@Parcelize
data class UserResult(

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("avatar_url")
    val avatar_url: String,
) : Parcelable

data class RepoResult(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("updated_at")
    val updated_at: String,

    @field:SerializedName("stargazers_count")
    val stargazers_count: String,
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