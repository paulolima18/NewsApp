package com.paulolima.bbc.data.repository

import com.paulolima.bbc.data.model.Article

class FakeMainRepository : MainRepositoryInterface {

    override suspend fun getArticle(): Article {
        return Article()
    }
}