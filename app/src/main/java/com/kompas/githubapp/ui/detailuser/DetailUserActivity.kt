package com.kompas.githubapp.ui.detailuser

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kompas.githubapp.BuildConfig
import com.kompas.githubapp.data.Result
import com.kompas.githubapp.data.local.entity.UserEntity
import com.kompas.githubapp.databinding.ActivityDetailUserBinding
import com.kompas.githubapp.databinding.ActivityUserlistBinding
import com.kompas.githubapp.viewmodel.ViewModelFactory

class DetailUserActivity : AppCompatActivity() {

    private var _binding: ActivityDetailUserBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        getData()
        setupView()


    }

    @SuppressLint("SetTextI18n")
    private fun getData() {

        val dataUser = intent.getParcelableExtra<UserEntity>("user") as UserEntity

        binding?.imageView?.let {
            Glide.with(this@DetailUserActivity)
                .load(dataUser.avatar_url)
                .into(it)
        }

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailUserViewModel::class.java]

        viewModel.getUserData(BuildConfig.GITHUB_TOKEN, dataUser.login).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding?.progressBar2?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding?.progressBar2?.visibility = View.GONE
                        binding?.txtNameUser?.text = result.data.name
                        binding?.txtTwitter?.text = "@${result.data.twitter_username}"
                        binding?.txtDescription?.text = result.data.bio

                        //after succes, get repo list with recylerview

                        getRepo(dataUser.login)

                    }
                    is Result.Error -> {
                        binding?.progressBar2?.visibility = View.GONE
                        Toast.makeText(
                            this,
                            result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

//        binding?.txtNameUser?.text = dataUser.login



    }

    private fun getRepo(name : String) {

        val repoAdapter = RepoListAdapter()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailUserViewModel::class.java]

        viewModel.getRepoList(BuildConfig.GITHUB_TOKEN, name).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding?.progressBar2?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding?.progressBar2?.visibility = View.GONE
                        val repoData = result.data
                        repoAdapter.submitList(repoData)
                    }
                    is Result.Error -> {
                        binding?.progressBar2?.visibility = View.GONE
                        Toast.makeText(
                            this,
                            result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding?.rvRepo?.apply {
            layoutManager = LinearLayoutManager(this@DetailUserActivity)
            setHasFixedSize(true)
            adapter = repoAdapter
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