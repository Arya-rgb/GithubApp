package com.kompas.githubapp.ui.detailuser

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kompas.githubapp.R
import com.kompas.githubapp.data.remote.response.RepoResult
import com.kompas.githubapp.databinding.ItemRepoBinding
import java.text.SimpleDateFormat
import java.time.OffsetDateTime

class RepoListAdapter : ListAdapter<RepoResult, RepoListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataRepo = getItem(position)
        holder.bind(dataRepo)
    }

    class ViewHolder(binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var repoName: TextView = itemView.findViewById(R.id.txt_repo_name)
        private var repoDescription: TextView = itemView.findViewById(R.id.txt_repo_description)
        private var repoStar: TextView = itemView.findViewById(R.id.txt_star)
        private var repoUpdated: TextView = itemView.findViewById(R.id.txt_updated)

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(dataAddress: RepoResult) {
            repoName.text = dataAddress.name
            repoDescription.text = dataAddress.description
            repoStar.text = dataAddress.stargazers_count

            val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
            val formattedDate = parser.parse(dataAddress.updated_at)?.let { formatter.format(it) }

            repoUpdated.text = "Updated At $formattedDate"

        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<RepoResult> =
            object : DiffUtil.ItemCallback<RepoResult>() {
                override fun areItemsTheSame(oldUser: RepoResult, newUser: RepoResult): Boolean {
                    return oldUser.name == newUser.name
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldUser: RepoResult,
                    newUser: RepoResult
                ): Boolean {
                    return oldUser == newUser
                }
            }
    }

}