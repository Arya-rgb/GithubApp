package com.kompas.githubapp.ui.userlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.kompas.githubapp.data.Result
import com.kompas.githubapp.data.Result.*
import com.kompas.githubapp.data.UserRepository
import com.kompas.githubapp.data.local.entity.UserEntity
import com.kompas.githubapp.utils.DataDummy
import com.kompas.githubapp.utils.getOrAwaitValue
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var userListViewModel: UserListViewModel
    private val dummyData = DataDummy.generateDummyUserEntity()

    @Before
    fun setUp() {
        userListViewModel = UserListViewModel(userRepository)
    }

    @Test
    fun `when Get User Should Not Null and Return Success`() {
        val expecteData = MutableLiveData<Result<List<UserEntity>>>()
        expecteData.value = Success(dummyData)
        `when`(userRepository.getUserList("apikey")).thenReturn(expecteData)

        val actualData = userListViewModel.getUserList("apikey").getOrAwaitValue()

        Mockito.verify(userRepository).getUserList("apikey")
        Assert.assertNotNull(actualData)
        Assert.assertTrue(actualData is Success)
        Assert.assertEquals(dummyData.size, (actualData as Success).data.size)
    }

    @Test
    fun `when Network Error Should Return Error`() {
        val userNews = MutableLiveData<Result<List<UserEntity>>>()
        userNews.value = Result.Error("Error")
        `when`(userRepository.getUserList("apikey")).thenReturn(userNews)

        val actualNews = userListViewModel.getUserList("apikey").getOrAwaitValue()
        Mockito.verify(userRepository).getUserList("apikey")
        Assert.assertNotNull(actualNews)
        Assert.assertTrue(actualNews is Result.Error)
    }


}