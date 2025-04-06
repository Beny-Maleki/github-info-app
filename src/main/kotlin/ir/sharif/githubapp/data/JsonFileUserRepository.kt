package ir.sharif.githubapp.data

import com.google.gson.Gson
import ir.sharif.githubapp.model.GitHubRepository
import ir.sharif.githubapp.model.GitHubUser
import java.io.File

class JsonFileUserRepository(
    private val gson: Gson = Gson()
) : IUserRepository {

    // The folder where each user is stored as <username>.json
    private val cacheDir = File("./cache")

    init {
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }
    }

    override fun saveUser(user: GitHubUser) {
        // Each user is saved to a file named <username>.json
        val filename = "${user.login.lowercase()}.json"
        val file = File(cacheDir, filename)
        val json = gson.toJson(user)
        file.writeText(json)
    }

    override fun getUser(login: String): GitHubUser? {
        val filename = "${login.lowercase()}.json"
        val file = File(cacheDir, filename)
        if (!file.exists()) return null

        val json = file.readText()
        return gson.fromJson(json, GitHubUser::class.java)
    }

    override fun getAllUsers(): List<GitHubUser> {
        // Find all *.json files in the cache folder
        val jsonFiles = cacheDir.listFiles { _, name ->
            name.endsWith(".json", ignoreCase = true)
        } ?: return emptyList()

        // Parse each file into a GitHubUser
        return jsonFiles.mapNotNull { file ->
            val json = file.readText()
            if (json.isBlank()) {
                null
            } else {
                try {
                    gson.fromJson(json, GitHubUser::class.java)
                } catch (e: Exception) {
                    println("Warning: Could not parse file ${file.name} - skipping.")
                    null
                }
            }
        }
    }

    override fun searchUsersByUsername(query: String): List<GitHubUser> {
        // Read all users from disk, then filter
        return getAllUsers().filter {
            it.login.contains(query, ignoreCase = true)
        }
    }

    override fun searchReposByName(query: String): List<GitHubRepository> {
        // Aggregate all repos from all users, filter by repo name
        return getAllUsers().flatMap { user -> user.publicRepos }
            .filter { repo -> repo.name.contains(query, ignoreCase = true) }
    }
}
