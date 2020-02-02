package ro.gabi.githubbrowser.network

import ro.gabi.githubbrowser.data.GithubRepository

object ResponseMappers {

    fun GetRepositoriesResponse.Repository.toDomain(): GithubRepository =
        GithubRepository(id, name, description ?: "", owner.toDomain(), url, forks ?: 0, stars ?: 0)

    fun GetRepositoriesResponse.Owner.toDomain(): GithubRepository.Owner =
        GithubRepository.Owner(id, name)

}