package com.kompas.githubapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kompas.githubapp.data.local.entity.UserEntity
import com.kompas.githubapp.data.local.room.UserDao

class FakeNewsDao : UserDao {

    private var userData = mutableListOf<UserEntity>()

    override fun getUser(): LiveData<List<UserEntity>> {
        val observableUser = MutableLiveData<List<UserEntity>>()
        observableUser.value = userData
        return observableUser
    }

    override suspend fun insertUser(user: List<UserEntity>) {
        TODO("Not yet implemented")
    }
}