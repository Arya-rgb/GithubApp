package com.kompas.githubapp.ui.userlist

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kompas.githubapp.BuildConfig
import com.kompas.githubapp.data.Result
import com.kompas.githubapp.databinding.ActivityUserlistBinding
import com.kompas.githubapp.viewmodel.ViewModelFactory

class UserListActivity : AppCompatActivity() {

    private var _binding: ActivityUserlistBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserlistBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupView()
        showListUser()

    }

    private fun showListUser() {

        val userListAdapter = UserListAdapter()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[UserListViewModel::class.java]

        viewModel.getUserList(BuildConfig.GITHUB_TOKEN).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        val userData = result.data
                        userListAdapter.submitList(userData)
                    }
                    is Result.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(
                            this,
                            result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@UserListActivity)
            setHasFixedSize(true)
            adapter = userListAdapter
        }

    }

    private fun setupView() {

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}