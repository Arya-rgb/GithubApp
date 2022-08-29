package com.kompas.githubapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kompas.githubapp.MainDispatcherRule
import com.kompas.githubapp.data.local.room.UserDao
import com.kompas.githubapp.data.remote.response.UserResult
import com.kompas.githubapp.data.remote.retrofit.ApiService
import com.kompas.githubapp.utils.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class UserRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var apiService: ApiService
    private lateinit var newsDao : UserDao
    private lateinit var newsRepository: UserRepository

    @Before
    fun setUp() {
        apiService = FakeApiService()
        newsDao = FakeNewsDao()
        newsRepository = UserRepository(apiService, newsDao)
    }

    @Test
    fun `when getUser Should Not Null`() = runTest {
        val expectedNews = DataDummy.generateDummyUserEntity()
        val actualData = apiService.getUserList("apiKey")
        Assert.assertNotNull(actualData)
        Assert.assertEquals(expectedNews.size, actualData.size)
    }

}