package ro.gabi.githubbrowser.network

import android.net.Uri
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ro.gabi.githubbrowser.data.GithubRepository
import ro.gabi.githubbrowser.data.RepoSortCriteria
import java.util.*

class ApiClient(private val apiService: ApiService) {

    fun getRepositories(query: String, sortBy: RepoSortCriteria): Single<List<GithubRepository>> {
        return apiService.getRepositories(query, sortBy.name.toLowerCase(Locale.getDefault()))
            .map { response ->
                with(ResponseMappers) {
                    response.items.map { it.toDomain() }
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getReadmeUrl(repo: GithubRepository): Single<Uri> {
        return apiService.getReadme(repo.fullName, repo.defaultBranch)
            .map { response -> Uri.parse(response.url) }
    }


}