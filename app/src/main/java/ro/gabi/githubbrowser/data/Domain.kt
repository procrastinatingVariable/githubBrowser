package ro.gabi.githubbrowser.data

import java.io.Serializable

data class GithubRepository(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String,
    val defaultBranch: String?,
    val owner: Owner,
    val url: String,
    val forks: Long,
    val stars: Long
) : Serializable {

    data class Owner(
        val id: Long,
        val name: String,
        val avatarUrl: String?
    ) : Serializable

}

enum class RepoSortCriteria {
    STARS
}