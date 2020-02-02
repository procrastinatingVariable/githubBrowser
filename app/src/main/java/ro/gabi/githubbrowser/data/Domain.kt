package ro.gabi.githubbrowser.data

class GithubRepository(
    val id: Long,
    val name: String,
    val description: String,
    val owner: Owner,
    val url: String,
    val forks: Long,
    val stars: Long
) {

    class Owner(
        val id: Long,
        val name: String
    )

}

enum class RepoSortCriteria {
    STARS
}