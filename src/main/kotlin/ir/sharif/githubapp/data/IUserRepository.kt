package ir.sharif.githubapp.data

import ir.sharif.githubapp.model.GitHubUser
import ir.sharif.githubapp.model.GitHubRepository

interface IUserRepository {
    fun saveUser(user: GitHubUser)
    fun getUser(login: String): GitHubUser?
    fun getAllUsers(): List<GitHubUser>
    fun searchUsersByUsername(query: String): List<GitHubUser>
    fun searchReposByName(query: String): List<GitHubRepository>
}
