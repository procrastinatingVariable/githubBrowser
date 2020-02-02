package ro.gabi.githubbrowser.features.repolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Single
import ro.gabi.githubbrowser.common.livedata.Resource
import ro.gabi.githubbrowser.common.livedata.toLiveData
import ro.gabi.githubbrowser.common.livedata.toResourceLiveData
import ro.gabi.githubbrowser.data.GithubRepository
import ro.gabi.githubbrowser.data.RepoSortCriteria
import ro.gabi.githubbrowser.data.repository.RepoRepository
import ro.gabi.githubbrowser.features.common.BaseViewModel

class RepositoryListViewModel(private val repoRepository: RepoRepository) : BaseViewModel() {

    private val refreshLd = MutableLiveData<Any?>(null)

    val repositoriesLd: LiveData<Resource<List<GithubRepository>>> = Transformations.switchMap(refreshLd) {
        repoRepository.getRepositories("android", RepoSortCriteria.STARS).toResourceLiveData()
    }

    fun refreshRepoList() {
        refreshLd.value = null
    }

}