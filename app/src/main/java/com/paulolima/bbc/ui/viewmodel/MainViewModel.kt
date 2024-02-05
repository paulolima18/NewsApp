package com.paulolima.bbc.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulolima.bbc.data.model.Article
import com.paulolima.bbc.data.repository.MainRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepositoryInterface
) : ViewModel(), MainViewModelInterface {

    override val uiState: MutableStateFlow<Article> = MutableStateFlow(Article())

    init {
        viewModelScope.launch(Dispatchers.Main) {
            uiState.update { oldState ->
                oldState.copy(headlines = mainRepository.getArticle().headlines)
            }
        }
    }

    override suspend fun fetchHeadlines() {
        try {
            viewModelScope.launch(Dispatchers.Main) {
                uiState.update { oldState ->
                    oldState.copy(headlines = mainRepository.getArticle().headlines)
                }
            }
        } catch (exception: Exception) {
            Log.e("TAG", "fetchHeadlines: $exception")
            // emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
        }
    }
}