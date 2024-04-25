package com.cornellappdev.android.eateryblue.ui.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rambl.model.News
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

// Define the NewsViewModel
class NewsModel : ViewModel() {
    val newsArticles = mutableStateOf<News?>(null)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=2a81a96a4ed24309961db8995ade6ee2")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                // Assuming you have a data class `News` to represent the news articles
                val adapter = moshi.adapter(News::class.java)

                // Parse the JSON response
                val news = adapter.fromJson(response.body!!.string())

                newsArticles.value = news
            }
        }
    }

    fun refresh(){
        viewModelScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=2a81a96a4ed24309961db8995ade6ee2")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                // Assuming you have a data class `News` to represent the news articles
                val adapter = moshi.adapter(News::class.java)

                // Parse the JSON response
                val news = adapter.fromJson(response.body!!.string())

                newsArticles.value = news
            }
        }
    }
}
