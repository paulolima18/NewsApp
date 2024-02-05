package com.paulolima.bbc.data.api

import com.paulolima.bbc.HeadlineParams
import com.paulolima.bbc.data.model.Article
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {

    @GET("top-headlines")
    suspend fun getArticles(
        @Query("sources") sources: String? = HeadlineParams.SOURCE,
        @Query("country") country: String? = HeadlineParams.COUNTRY,
    ): Article
}