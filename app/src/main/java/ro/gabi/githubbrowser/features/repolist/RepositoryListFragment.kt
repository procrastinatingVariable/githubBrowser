package ro.gabi.githubbrowser.features.repolist


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_repository_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


import ro.gabi.githubbrowser.R
import ro.gabi.githubbrowser.common.livedata.ResourceObserver
import ro.gabi.githubbrowser.data.GithubRepository
import ro.gabi.githubbrowser.features.common.BaseFragment
import ro.gabi.githubbrowser.features.common.RecyclerViewAdapterFactory

class RepositoryListFragment : BaseFragment() {

    private val viewModel: RepositoryListViewModel by viewModel()

    private val adapterFactory: RecyclerViewAdapterFactory by inject()
    private val repoAdapter: RepoListAdapter by lazy {
        adapterFactory.create(requireContext(), RepoListAdapter::class).apply {
            onItemClick = { goToDetails(it) }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapterFactory.create(context, RepoListAdapter::class)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupActions()
    }

    private fun setupList() {
        repositoryListRv.adapter = repoAdapter
        repositoryListRv.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
    }

    private fun setupActions() {
        swipeRefreshLayout.setOnRefreshListener(::refreshList)
        swipeRefreshLayout.isRefreshing = true
    }

    private fun refreshList() {
        viewModel.refreshRepoList()
        swipeRefreshLayout.isRefreshing = true
    }

    override fun observeViewModel() {
        viewModel.repositoriesLd.observe(viewLifecycleOwner, ResourceObserver.onChanged(
            onSuccess = { repos ->
                repoAdapter.submitList(repos)
                swipeRefreshLayout.isRefreshing = false
            },
            onError = {
                Toast.makeText(context, getString(R.string.generic_error), Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false
            }
        ))
    }

    private fun goToDetails(repo: GithubRepository) {
        val action = RepositoryListFragmentDirections.actionRepositoryListFragmentToRepositoryDetailsFragment(repo)
        findNavController().navigate(action)
    }
}
