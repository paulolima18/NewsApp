package com.paulolima.bbc.ui.viewmodel

import com.paulolima.bbc.data.model.Article
import com.paulolima.bbc.data.model.Headline
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeViewModel(
    override val uiState: StateFlow<Article> = MutableStateFlow(Article(listOf(Headline())))
) : MainViewModelInterface {

    override suspend fun fetchHeadlines() {
        TODO("Not yet implemented")
    }
}