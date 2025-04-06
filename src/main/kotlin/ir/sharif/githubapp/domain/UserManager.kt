package ir.sharif.githubapp.domain

import ir.sharif.githubapp.data.IUserRepository
import ir.sharif.githubapp.data.JsonFileUserRepository
import ir.sharif.githubapp.data.InMemoryUserRepository
import ir.sharif.githubapp.model.GitHubRepository
import ir.sharif.githubapp.model.GitHubUser
import ir.sharif.githubapp.network.GitHubApiClient
import retrofit2.Response

const val CACHE_POLICY = "FILE" // or "MEMORY"

class UserManager {

    private val userRepository: IUserRepository = when(CACHE_POLICY){
        "FILE" -> JsonFileUserRepository()
        else -> InMemoryUserRepository()
    }

    /**
     * Fetches user data from cache if present, otherwise makes an API call,
     * fetches repos, caches the user, then returns it.
     */
    fun getUserData(username: String): GitHubUser {
        val cachedUser = userRepository.getUser(username)
        if (cachedUser != null) {
            return cachedUser
        }

        // Not in cache, make an API request
        val userResponse = GitHubApiClient.apiService.getUser(username).executeOrThrow()
        // Now fetch the user's repos
        val reposResponse = GitHubApiClient.apiService.getUserRepos(username).executeOrThrow()

        val user = userResponse.apply {
            publicRepos = reposResponse
        }
        // Cache the user
        userRepository.saveUser(user)

        return user
    }

    fun getAllCachedUsers(): List<GitHubUser> {
        return userRepository.getAllUsers()
    }

    fun searchUsersByUsername(query: String): List<GitHubUser> {
        return userRepository.searchUsersByUsername(query)
    }

    fun searchReposByName(query: String): List<GitHubRepository> {
        return userRepository.searchReposByName(query)
    }
}

/**
 * Extension function to handle sync calls and throw an exception if not successful.
 */
private fun <T> retrofit2.Call<T>.executeOrThrow(): T {
    val response: Response<T> = this.execute()
    if (response.isSuccessful && response.body() != null) {
        return response.body()!!
    } else {
        throw Exception("API call failed with code: ${response.code()} | ${response.message()}")
    }
}
