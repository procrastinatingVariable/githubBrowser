package ro.gabi.githubbrowser.features.repodetails


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_repository_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

import ro.gabi.githubbrowser.R
import ro.gabi.githubbrowser.common.livedata.ResourceObserver
import ro.gabi.githubbrowser.data.GithubRepository
import ro.gabi.githubbrowser.databinding.FragmentRepositoryDetailsBinding
import ro.gabi.githubbrowser.features.common.BaseFragment
import ro.gabi.githubbrowser.features.common.PageStatusUpdateWebViewClient

class RepositoryDetailsFragment : BaseFragment() {

    private val args: RepositoryDetailsFragmentArgs by navArgs()

    private val viewModel: RepositoryDetailsViewModel by viewModel()

    private lateinit var binding: FragmentRepositoryDetailsBinding

    private val progressIndicatorClient = PageStatusUpdateWebViewClient()

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
        setupFields()
        setupWebView()
    }

    private fun setupFields() {
        repoNameTv.setOnClickListener {
            viewModel.repositoryLd.value?.let {
                openLink(it)
            }
        }
    }

    private fun setupWebView() {
        readmeWv.webViewClient = progressIndicatorClient.apply {
            pageStatusLiveData.observe(viewLifecycleOwner, Observer { pageStatus ->
                when(pageStatus) {
                    PageStatusUpdateWebViewClient.PageStatus.STARTED -> loader.show()
                    PageStatusUpdateWebViewClient.PageStatus.FINISHED-> loader.hide()
                }
            })
        }

    }

    fun openLink(repo: GithubRepository) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(repo.url)
        }
        startActivity(intent)
    }

    override fun observeViewModel() {
        viewModel.readmeUri.observe(viewLifecycleOwner, ResourceObserver.onChanged(
            onSuccess = { readmeUri -> readmeWv.loadUrl(readmeUri.toString()) },
            onError = { Toast.makeText(context, R.string.no_readme, Toast.LENGTH_SHORT).show() }
        ))
    }

    private fun ProgressBar.hide() {
        visibility = View.GONE
    }

    private fun ProgressBar.show() {
        visibility = View.VISIBLE
    }
}
