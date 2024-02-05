package com.paulolima.bbc.data.api

import com.paulolima.bbc.data.model.Article

class FakeNewsService : NewsService {
    override suspend fun getArticles(sources: String?, country: String?): Article {
        return Article()
    }
}