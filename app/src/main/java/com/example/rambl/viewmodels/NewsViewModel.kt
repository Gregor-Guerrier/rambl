package com.cornellappdev.android.eateryblue.ui.viewmodels

import android.os.Debug
import android.util.Log
import androidx.lifecycle.ViewModel
import com.cornellappdev.android.eateryblue.ui.model.NewsModel
import com.example.rambl.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.rambl.model.News

class NewsViewModel(
    val newsModel: NewsModel
) : ViewModel() {

    sealed class State {
        data class Unselected(
            val idk : String
        ) : State()
        data class Selected(
            val userId : Int
        ) : State()
    }

    private val _ramblFlow: MutableStateFlow<List<Article>> = MutableStateFlow(listOf())

    /**
     * A flow of filters applied to the screen.
     */
    val ramblFLow = _ramblFlow.asStateFlow()

    fun updateNews(){
        _ramblFlow.value = newsModel.newsArticles.value?.articles ?: listOf()
    }


}