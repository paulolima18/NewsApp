package com.paulolima.bbc.data.repository

import com.paulolima.bbc.data.api.NewsService
import com.paulolima.bbc.data.model.Article
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: NewsService
) : MainRepositoryInterface {

    override suspend fun getArticle(): Article = apiService.getArticles()
}