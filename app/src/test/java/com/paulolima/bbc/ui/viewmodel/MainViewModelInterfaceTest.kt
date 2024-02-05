package com.paulolima.bbc.ui.viewmodel

import com.paulolima.bbc.MainDispatcherRule
import com.paulolima.bbc.data.model.Article
import com.paulolima.bbc.data.repository.FakeMainRepository
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainViewModelInterfaceTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun mainViewModel_Initialization_ArticlesLoaded() {
        val viewModel: MainViewModelInterface = MainViewModel(mainRepository = FakeMainRepository())

        val viewModelHeadlines = viewModel.uiState.value.headlines
        val headlines = Article().headlines

        // Assert that articles are parsed correctly.
        assertEquals(headlines, viewModelHeadlines)
    }
}