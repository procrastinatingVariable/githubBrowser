package ro.gabi.githubbrowser.features.repodetails


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_repository_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

import ro.gabi.githubbrowser.R
import ro.gabi.githubbrowser.data.GithubRepository
import ro.gabi.githubbrowser.databinding.FragmentRepositoryDetailsBinding
import ro.gabi.githubbrowser.features.common.BaseFragment

class RepositoryDetailsFragment : BaseFragment() {

    private val args: RepositoryDetailsFragmentArgs by navArgs()

    private val viewModel: RepositoryDetailsViewModel by viewModel()

    private lateinit var binding: FragmentRepositoryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setRepository(args.githubRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentRepositoryDetailsBinding>(inflater,
            R.layout.fragment_repository_details,
            container,
            false)
            .apply {
                viewModel = this@RepositoryDetailsFragment.viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repoNameTv.setOnClickListener {
            viewModel.repositoryLd.value?.let {
                openLink(it)
            }
        }
    }

    fun openLink(repo: GithubRepository) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(repo.url)
        }
        startActivity(intent)
    }

    override fun observeViewModel() {
    }
}
