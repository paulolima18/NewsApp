package com.paulolima.bbc.ui.view

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.paulolima.bbc.data.model.Headline
import com.paulolima.bbc.data.model.Source
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HeadlineCardTest {

    @get:Rule(order = 2)
    val composeTestRule = createComposeRule()

    @Test
    fun testHeadlineCardIsDisplayed() {
        setScreen(
            headline = Headline(
                source = Source(name = "Name"),
                title = "Title",
                urlToImage = "www.google.com",
                publishedAt = "date"
            )
        )

        composeTestRule.onNodeWithTag(HeadlineIdentifier.CARD).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.CARD_TITLE_TEXT, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.CARD_IMAGE, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.CARD_SOURCE_TEXT, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.CARD_DATE_TEXT, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun testHeadlineCardDoesNotExist() {
        setScreen()

        composeTestRule.onNodeWithTag(HeadlineIdentifier.CARD).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.CARD_TITLE_TEXT, useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.CARD_IMAGE, useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.CARD_SOURCE_TEXT, useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.CARD_DATE_TEXT, useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun testHeadlineCardIsClicked() {
        var wasActionReceived = false

        setScreen(onClick = {
            wasActionReceived = true
        })

        composeTestRule.onNodeWithTag(HeadlineIdentifier.CARD).assertIsDisplayed().performClick()

        assert(wasActionReceived)
    }

    private fun setScreen(headline: Headline = Headline(), onClick: () -> Unit = {}) {
        composeTestRule.setContent {
            HeadlineCard(
                headline = headline,
                onClick = onClick
            )
        }
    }
}