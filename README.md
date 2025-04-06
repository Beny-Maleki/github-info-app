# GitHub User Info CLI

A Kotlin-based terminal application that retrieves and caches GitHub user information (username, followers, following, creation date, and public repositories) to avoid repeated requests. It uses **Retrofit** for API communication and **Gson** for JSON serialization.

## Features

1. **Get user info by username**
    - Fetches user information from GitHub’s API.
    - Caches the data in memory to avoid repeated API requests.
2. **Display list of cached users**
    - Shows all users already cached.
3. **Search cached users by username**
    - Searches among users fetched so far.
4. **Search cached repositories by name**
    - Looks through repositories belonging to cached users.
5. **Exit**
    - Exits the CLI.


## How to Build and Run

1. **Clone or download** this repository.

2. **Ensure your source code follows the directory structure:**

    ```
    .
    └── src
        └── main
            └── kotlin
                └── ir
                    └── sharif
                        └── githubapp
                            ├── Application.kt  (or Main.kt)
                            ├── domain
                            │   └── UserManager.kt
                            ├── data
                            │   └── UserRepository.kt
                            ├── model
                            │   ├── GitHubUser.kt
                            │   └── GitHubRepository.kt
                            └── network
                                ├── GitHubClient.kt
                                └── GitHubService.kt
    ```

   *Adjust packages as needed; ensure the `<mainClass>` in your `pom.xml` matches your main class.*

3. **Build the project:**

    ```bash
    mvn clean install
    ```

   This command will compile the Kotlin code and package everything into a JAR file in the `target/` folder.

4. **Run the application:**

    - **Option 1:** Using the Maven Exec Plugin (if configured):

      ```bash
      mvn exec:java -Dexec.mainClass="ir.sharif.githubapp.ApplicationKt"
      ```

    - **Option 2:** Running the generated JAR directly:

      ```bash
      java -jar target/consoleApp-1.0-SNAPSHOT.jar
      ```

      *(Ensure dependencies are on the classpath if you’re not using a shaded/fat JAR.)*

## Example Usage

Below is a sample run using this CLI. In this example, information for two GitHub users (“Beny-Maleki” and “hirbodbehnam”) is fetched.

```shell
Connected to the target VM, address: '127.0.0.1:3665', transport: 'socket'
=== GitHub User Info CLI ===
1) Get user info by username
2) Display list of cached users
3) Search cached users by username
4) Search cached repositories by name
5) Exit
Enter choice: 1
Enter GitHub username: Beny-Maleki
User: Beny-Maleki
Followers: 14
Following: 19
Created at: 2021-02-18T13:49:29Z
Public Repos: 
 - Artificial-Intelligence-Assignments
 - Beny-Maleki
 - beny-maleki.github.io
 - Course-Descriptions
 - Data-Structure-and-Algorithms-Project
 - Detoxifying-Text-Paradetox
 - Finetuned-MPNet-on-Fake-News-Dataset
 - OSProject_MultiThreadedServer
 - Pix2PixTorch
 - Torch3D-and-Rendering
 - Wallet-Manager
 - Yu-Gi-Oh-Final-Phase

=== GitHub User Info CLI ===
1) Get user info by username
2) Display list of cached users
3) Search cached users by username
4) Search cached repositories by name
5) Exit
Enter choice: 2
Users in cache:
 - Beny-Maleki

=== GitHub User Info CLI ===
1) Get user info by username
2) Display list of cached users
3) Search cached users by username
4) Search cached repositories by name
5) Exit
Enter choice: 1
Enter GitHub username: hirbodbehnam
User: HirbodBehnam
Followers: 459
Following: 64
Created at: 2015-03-17T11:45:11Z
Public Repos: 
 - 2048-cli-c
 - ack_chan
 - Address-Book
 - AdvancedOS-Assignments
 - AI-Assignments
 - Anal-Midterm
 - AP-Codes
 - APTester
 - ArabicFilenameFixer
 - ASM-Tookit
 - AsyncPipe
 - bchan
 - BlackLotus
 - CaptchaBot
 - CE-Codes
 - CE-Site
 - Chat-Go
 - CMinusLLVM
 - Compiler-Assignments
 - Computer-Simulation-Assignments
 - CourseEnrollment
 - CrowFS
 - CrowOS
 - CSharpRandom
 - CSL-Assignments
 - CUDA-Prime-Detector
 - DB-Assignments
 - Decypher
 - Deemix-Bot
 - Deemix-Discord-Bot

=== GitHub User Info CLI ===
1) Get user info by username
2) Display list of cached users
3) Search cached users by username
4) Search cached repositories by name
5) Exit
Enter choice: 3
Enter username to search: hirbodbehnam
Matching users:
 - HirbodBehnam

=== GitHub User Info CLI ===
1) Get user info by username
2) Display list of cached users
3) Search cached users by username
4) Search cached repositories by name
5) Exit
Enter choice: 4
Enter GitHubRepository name to search: Detoxify
Matching repositories:
 - Detoxifying-Text-Paradetox, Owner: Beny-Maleki

=== GitHub User Info CLI ===
1) Get user info by username
2) Display list of cached users
3) Search cached users by username
4) Search cached repositories by name
5) Exit
Enter choice:

```