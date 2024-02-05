package com.paulolima.bbc.ui.viewmodel

import com.paulolima.bbc.data.model.Article
import kotlinx.coroutines.flow.StateFlow

interface MainViewModelInterface {

    val uiState: StateFlow<Article>

    suspend fun fetchHeadlines()
}