package ro.gabi.githubbrowser.network

import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET("search/repositories")
    fun getRepositories(@Query("q") query: String, @Query("sort") sortBy: String): Single<GetRepositoriesResponse>

    @GET("repos/{full_name}/readme")
    fun getReadme(@Path("full_name", encoded = true) fullName: String, @Query("default_branch") defaultBranch: String?): Single<GetReadmeResponse>

}