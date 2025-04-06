package ir.sharif.githubapp.network

import ir.sharif.githubapp.model.GitHubUser
import ir.sharif.githubapp.model.GitHubRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {
    // GET /users/{username}
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<GitHubUser>

    // GET /users/{username}/repos
    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Call<List<GitHubRepository>>
}
