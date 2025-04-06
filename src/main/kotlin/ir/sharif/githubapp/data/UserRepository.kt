package ir.sharif.githubapp.data

import ir.sharif.githubapp.model.GitHubUser
import ir.sharif.githubapp.model.GitHubRepository

class UserRepository {
    // In-memory cache
    private val userCache = mutableMapOf<String, GitHubUser>()

    fun saveUser(user: GitHubUser) {
        userCache[user.login.lowercase()] = user
    }

    fun getUser(login: String): GitHubUser? {
        return userCache[login.lowercase()]
    }

    fun getAllUsers(): List<GitHubUser> {
        return userCache.values.toList()
    }

    fun searchUsersByUsername(query: String): List<GitHubUser> {
        return userCache.values.filter {
            it.login.contains(query, ignoreCase = true)
        }
    }

    fun searchReposByName(query: String): List<GitHubRepository> {
        return userCache.values.flatMap { user ->
            user.publicRepos
        }.filter { repo ->
            repo.name.contains(query, ignoreCase = true)
        }
    }
}
