package com.kompas.githubapp.ui.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kompas.githubapp.R
import com.kompas.githubapp.databinding.ActivityUserlistBinding

class UserListActivity : AppCompatActivity() {

    private var _binding: ActivityUserlistBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserlistBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        

    }

}