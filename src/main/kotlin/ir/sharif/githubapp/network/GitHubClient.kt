package ir.sharif.githubapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitHubApiClient {

    private const val BASE_URL = "https://api.github.com/"

//    private val loggingInterceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BASIC
//    }

    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: GitHubApiService by lazy {
        retrofit.create(GitHubApiService::class.java)
    }
}
