package ro.gabi.githubbrowser.features.repodetails

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import io.reactivex.android.schedulers.AndroidSchedulers
import ro.gabi.githubbrowser.common.livedata.Resource
import ro.gabi.githubbrowser.common.livedata.toResourceLiveData
import ro.gabi.githubbrowser.data.GithubRepository
import ro.gabi.githubbrowser.features.common.BaseViewModel
import ro.gabi.githubbrowser.network.ApiClient

class RepositoryDetailsViewModel(private val apiClient: ApiClient) : BaseViewModel() {

    private val _repositoryLd = MutableLiveData<GithubRepository>()
    val repositoryLd: LiveData<GithubRepository>
        get() = _repositoryLd

    val readmeUri: LiveData<Resource<Uri>> = Transformations.switchMap(repositoryLd) { repo ->
        apiClient.getReadmeUrl(repo)
            .toResourceLiveData()
    }


    fun setRepository(githubRepository: GithubRepository) {
        _repositoryLd.value = githubRepository
    }
}
