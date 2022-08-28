package com.kompas.githubapp.ui.userlist

import androidx.lifecycle.ViewModel
import com.kompas.githubapp.data.UserRepository
import com.kompas.githubapp.viewmodel.ViewModelFactory

class UserListViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUserList(apiKey: String) = userRepository.getUserList(apiKey)

    fun searchUser(apiKey: String, query: String) = userRepository.getSearchData(apiKey, query)

}