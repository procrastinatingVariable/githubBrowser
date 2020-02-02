package ro.gabi.githubbrowser.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    fun getRepositories(@Query("q") query: String, @Query("sort") sortBy: String): Single<GetRepositoriesResponse>

    @GET("repos/{full_name}/readme")
    fun getReadme(@Path("full_name") fullName: String)

}