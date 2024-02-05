package com.paulolima.bbc.data.repository

import com.paulolima.bbc.data.api.FakeNewsService
import com.paulolima.bbc.data.model.Article
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MainRepositoryTest {

    @Test
    fun `maps user types correctly`() = runTest {
        val repository: MainRepositoryInterface = MainRepository(FakeNewsService())

        val article = repository.getArticle()

        assertEquals(Article(), article)
    }
}