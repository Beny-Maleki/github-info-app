package ir.sharif.githubapp

import ir.sharif.githubapp.domain.UserManager
import java.util.*

fun main() {
    val userManager = UserManager()
    val scanner = Scanner(System.`in`)

    while (true) {
        println("=== GitHub User Info CLI ===")
        println("1) Get user info by username")
        println("2) Display list of cached users")
        println("3) Search cached users by username")
        println("4) Search cached repositories by name")
        println("5) Exit")

        print("Enter choice: ")
        when (scanner.nextLine().trim()) {
            "1" -> {
                print("Enter GitHub username: ")
                val username = scanner.nextLine().trim()
                try {
                    val user = userManager.getUserData(username)
                    println("User: ${user.login}")
                    println("Followers: ${user.followers}")
                    println("Following: ${user.following}")
                    println("Created at: ${user.createdAt}")
                    println("Public Repos: ")
                    user.publicRepos.forEach {
                        println(" - ${it.name}")
                    }
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
            "2" -> {
                val users = userManager.getAllCachedUsers()
                if (users.isEmpty()) {
                    println("No users in cache.")
                } else {
                    println("Users in cache:")
                    users.forEach {
                        println(" - ${it.login}")
                    }
                }
            }
            "3" -> {
                print("Enter username to search: ")
                val query = scanner.nextLine().trim()
                val matchingUsers = userManager.searchUsersByUsername(query)
                if (matchingUsers.isEmpty()) {
                    println("No matching users found.")
                } else {
                    println("Matching users:")
                    matchingUsers.forEach {
                        println(" - ${it.login}")
                    }
                }
            }
            "4" -> {
                print("Enter GitHubRepository name to search: ")
                val query = scanner.nextLine().trim()
                val matchingRepos = userManager.searchReposByName(query)
                if (matchingRepos.isEmpty()) {
                    println("No matching repositories found.")
                } else {
                    println("Matching repositories:")
                    matchingRepos.forEach {
                        println(" - ${it.name}, Owner: ${it.ownerLogin}")
                    }
                }
            }
            "5" -> {
                println("Exiting...")
                break
            }
            else -> println("Invalid choice.")
        }

        println()
    }
}
