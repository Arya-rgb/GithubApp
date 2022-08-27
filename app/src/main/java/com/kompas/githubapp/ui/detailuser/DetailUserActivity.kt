package com.kompas.githubapp.ui.detailuser

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.kompas.githubapp.data.local.entity.UserEntity
import com.kompas.githubapp.databinding.ActivityDetailUserBinding
import com.kompas.githubapp.databinding.ActivityUserlistBinding

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

    private fun getData() {

        val dataUser = intent.getParcelableExtra<UserEntity>("user") as UserEntity

        binding?.imageView?.let {
            Glide.with(this@DetailUserActivity)
                .load(dataUser.avatar_url)
                .into(it)
        }

        binding?.txtNameUser?.text = dataUser.login

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