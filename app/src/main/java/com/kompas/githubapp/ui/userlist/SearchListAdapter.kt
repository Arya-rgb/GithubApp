package com.kompas.githubapp.ui.userlist

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kompas.githubapp.R
import com.kompas.githubapp.data.local.entity.UserEntity
import com.kompas.githubapp.data.remote.response.UserResult
import com.kompas.githubapp.databinding.ItemUserlistBinding
import com.kompas.githubapp.ui.detailuser.DetailUserActivity

//for search, the data not saved in database, so need to create new adapter
class SearchListAdapter : ListAdapter<UserResult, SearchListAdapter.ViewHolder>(DIFF_CALLBACK)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemViewBinding =
            ItemUserlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemViewBinding)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dataUser = getItem(position)
        holder.bind(dataUser)

    }

    class ViewHolder(binding: ItemUserlistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var imgPhoto: ImageView = itemView.findViewById(R.id.tv_image_profile)
        private var userName: TextView = itemView.findViewById(R.id.text_user_name)

        fun bind(dataUser: UserResult) {
            Glide.with(itemView.context)
                .load(dataUser.avatar_url)
                .into(imgPhoto)

            userName.text = dataUser.login

            itemView.setOnClickListener {
                //for test
                Toast.makeText(itemView.context, dataUser.login, Toast.LENGTH_SHORT).show()

                val moveDetail = Intent(itemView.context, DetailUserActivity::class.java).apply {
                    putExtra("username", dataUser.login)
                    putExtra("photoUrl", dataUser.avatar_url)
                }

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair(imgPhoto, "photoUrl"),
                        Pair(userName, "name")
                    )

                itemView.context.startActivity(moveDetail, optionsCompat.toBundle())

            }

        }


    }


    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<UserResult> =
            object : DiffUtil.ItemCallback<UserResult>() {
                override fun areItemsTheSame(oldUser: UserResult, newUser: UserResult): Boolean {
                    return oldUser.id == newUser.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldUser: UserResult,
                    newUser: UserResult
                ): Boolean {
                    return oldUser == newUser
                }
            }
    }



}