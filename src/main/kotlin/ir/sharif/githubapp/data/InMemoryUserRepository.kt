package ir.sharif.githubapp.data

import ir.sharif.githubapp.model.GitHubUser
import ir.sharif.githubapp.model.GitHubRepository

class InMemoryUserRepository: IUserRepository {
    // In-memory cache
    private val userCache = mutableMapOf<String, GitHubUser>()

    override fun saveUser(user: GitHubUser) {
        userCache[user.login.lowercase()] = user
    }

    override fun getUser(login: String): GitHubUser? {
        return userCache[login.lowercase()]
    }

    override fun getAllUsers(): List<GitHubUser> {
        return userCache.values.toList()
    }

    override fun searchUsersByUsername(query: String): List<GitHubUser> {
        return userCache.values.filter {
            it.login.contains(query, ignoreCase = true)
        }
    }

    override fun searchReposByName(query: String): List<GitHubRepository> {
        return userCache.values.flatMap { user ->
            user.publicRepos
        }.filter { repo ->
            repo.name.contains(query, ignoreCase = true)
        }
    }
}
