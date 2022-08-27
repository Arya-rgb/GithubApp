package com.kompas.githubapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kompas.githubapp.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_data ORDER BY id ASC")
    fun getUser(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: List<UserEntity>)

}