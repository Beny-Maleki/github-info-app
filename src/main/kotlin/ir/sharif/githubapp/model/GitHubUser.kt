package ir.sharif.githubapp.model

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    val login: String,
    val followers: Int,
    val following: Int,
    @SerializedName("created_at")
    val createdAt: String,
    // We'll keep the list of public repos in a separate data structure, or you could
    // store them here after fetching. But for clarity, let's store them here:
    var publicRepos: List<GitHubRepository> = emptyList()
)
