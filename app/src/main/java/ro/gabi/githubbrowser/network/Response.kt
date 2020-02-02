package ro.gabi.githubbrowser.network

import com.google.gson.annotations.SerializedName

data class GetRepositoriesResponse(
    val items: List<Repository>
) {

    data class Repository(
        val id: Long,
        val name: String,
        @SerializedName("full_name") val fullName: String,
        val owner: Owner,
        @SerializedName("html_url") val url: String,
        val description: String?,
        @SerializedName("default_branch") val defaultBranch: String?,
        @SerializedName("watchers") val stars: Long?,
        @SerializedName("forks") val forks: Long?
    )

    data class Owner(
        val id: Long,
        @SerializedName("login") val name: String,
        @SerializedName("avatar_url") val avatarUrl: String?
    )

}