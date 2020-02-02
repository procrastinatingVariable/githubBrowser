package ro.gabi.githubbrowser.features.repolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.item_github_repository.view.*
import ro.gabi.githubbrowser.R
import ro.gabi.githubbrowser.common.util.FunNumberFormatter
import ro.gabi.githubbrowser.data.GithubRepository
import ro.gabi.githubbrowser.features.common.ListItemViewHolder

typealias OnItemClickListener = (GithubRepository) -> Unit

class RepoListAdapter(context: Context, var onItemClick: OnItemClickListener = {}) :
    ListAdapter<GithubRepository, RepoListAdapter.RepoViewHolder>(diffUtil) {

    private var layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder =
        RepoViewHolder(
            layoutInflater.inflate(R.layout.item_github_repository, parent, false),
            onItemClick
        )

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class RepoViewHolder(view: View, private val onItemClick: OnItemClickListener) : ListItemViewHolder<GithubRepository>(view) {

        override fun bind(data: GithubRepository) {
            containerView.repoNameTv.text = data.name
            containerView.userNameTv.text = data.owner.name
            containerView.descriptionTv.text = data.description
            containerView.starsTv.text = FunNumberFormatter.format(data.stars)
            containerView.forksTv.text = FunNumberFormatter.format(data.forks)
            containerView.setOnClickListener { onItemClick(data) }
        }

    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<GithubRepository>() {
            override fun areItemsTheSame(
                oldItem: GithubRepository,
                newItem: GithubRepository
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: GithubRepository,
                newItem: GithubRepository
            ): Boolean = oldItem.equals(newItem)
        }
    }

}