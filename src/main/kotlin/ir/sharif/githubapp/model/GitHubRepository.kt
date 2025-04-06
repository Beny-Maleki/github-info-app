package ir.sharif.githubapp.model

import com.google.gson.annotations.SerializedName

data class GitHubRepository(
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    // Additional fields as needed
    // We can also store the owner's login for easier searching
    @SerializedName("owner")
    val owner: Owner
) {
    val ownerLogin: String
        get() = owner.login
}

data class Owner(
    val login: String
)
