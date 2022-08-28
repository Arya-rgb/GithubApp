package com.kompas.githubapp.ui.detailuser

import androidx.lifecycle.ViewModel
import com.kompas.githubapp.data.UserRepository

class DetailUserViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUserData(apiKey: String, name: String) = userRepository.getDataUser(apiKey, name)

    fun getRepoList(apiKey: String, name: String) = userRepository.getRepoList(apiKey, name)

}