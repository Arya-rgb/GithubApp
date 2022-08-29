package com.kompas.githubapp.ui.detailuser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.kompas.githubapp.data.Result
import com.kompas.githubapp.data.UserRepository
import com.kompas.githubapp.data.local.entity.UserEntity
import com.kompas.githubapp.data.remote.response.RepoResult
import com.kompas.githubapp.ui.userlist.UserListViewModel
import com.kompas.githubapp.utils.DataDummy
import com.kompas.githubapp.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailUserViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var detailUserViewModel: DetailUserViewModel
    private val repoUserDummy = DataDummy.generateDummyRepo()

    @Before
    fun setUp() {
        detailUserViewModel = DetailUserViewModel(userRepository)
    }

    @Test
    fun `when Get Repo Should Not Null and Return Success`() {
        val expecteData = MutableLiveData<Result<List<RepoResult>>>()
        expecteData.value = Result.Success(repoUserDummy)
        Mockito.`when`(userRepository.getRepoList("apikey", "name")).thenReturn(expecteData)

        val actualData = detailUserViewModel.getRepoList("apikey", "name").getOrAwaitValue()

        Mockito.verify(userRepository).getRepoList("apikey", "name")
        Assert.assertNotNull(actualData)
        Assert.assertTrue(actualData is Result.Success)
        Assert.assertEquals(repoUserDummy.size, (actualData as Result.Success).data.size)
    }


    @Test
    fun `when Network Error Should Return Error`() {
        val userRepo = MutableLiveData<Result<List<RepoResult>>>()
        userRepo.value = Result.Error("Error")
        Mockito.`when`(userRepository.getRepoList("apikey", "name")).thenReturn(userRepo)

        val actualNews = detailUserViewModel.getRepoList("apikey", "name").getOrAwaitValue()
        Mockito.verify(userRepository).getRepoList("apikey", "name")
        Assert.assertNotNull(actualNews)
        Assert.assertTrue(actualNews is Result.Error)
    }




}