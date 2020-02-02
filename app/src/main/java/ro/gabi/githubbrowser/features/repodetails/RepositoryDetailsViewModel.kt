package ro.gabi.githubbrowser.features.repodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ro.gabi.githubbrowser.data.GithubRepository
import ro.gabi.githubbrowser.features.common.BaseViewModel

class RepositoryDetailsViewModel : BaseViewModel() {

    private val _repositoryLd = MutableLiveData<GithubRepository>()
    val repositoryLd: LiveData<GithubRepository>
        get() = _repositoryLd

    fun setRepository(githubRepository: GithubRepository) {
        _repositoryLd.value = githubRepository
    }
}
