package com.kompas.githubapp.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_data")
@Parcelize
data class UserEntity(

    @field:ColumnInfo(name = "login")
    val login: String,

    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    val id: Int,

    @field:ColumnInfo(name = "avatar_url")
    val avatar_url: String,

) : Parcelable