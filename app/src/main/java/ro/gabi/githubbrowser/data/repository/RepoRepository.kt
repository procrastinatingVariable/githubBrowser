package ro.gabi.githubbrowser.data.repository

import io.reactivex.Single
import ro.gabi.githubbrowser.data.GithubRepository
import ro.gabi.githubbrowser.data.RepoSortCriteria

interface RepoRepository {

    fun getRepositories(query: String, sortBy: RepoSortCriteria): Single<List<GithubRepository>>

}