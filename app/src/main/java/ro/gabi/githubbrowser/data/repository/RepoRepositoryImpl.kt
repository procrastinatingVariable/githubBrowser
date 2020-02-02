package ro.gabi.githubbrowser.data.repository

import io.reactivex.Single
import ro.gabi.githubbrowser.data.GithubRepository
import ro.gabi.githubbrowser.data.RepoSortCriteria
import ro.gabi.githubbrowser.network.ApiClient

class RepoRepositoryImpl(private val apiClient: ApiClient) : RepoRepository {

    override fun getRepositories(query: String, sortBy: RepoSortCriteria): Single<List<GithubRepository>> {
        return apiClient.getRepositories(query, sortBy)
    }

}