package com.paulolima.bbc.data.repository

import com.paulolima.bbc.data.model.Article

interface MainRepositoryInterface {

    suspend fun getArticle(): Article
}