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
class HeadlineInfoScreenTest {

    @get:Rule(order = 2)
    val composeTestRule = createComposeRule()

    @Test
    fun testHeadlineCardIsDisplayed() {
        setScreen(
            headline = Headline(
                source = Source(name = "Name"),
                title = "Title",
                author = "Author",
                content = "Content",
                urlToImage = "www.google.com",
                publishedAt = "date"
            )
        )

        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_POST, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_POST_AUTHOR, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_POST_CONTENT, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_POST_IMAGE, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_POST_TITLE, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_TOP_BAR, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_TOP_BAR_BUTTON, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_TOP_BAR_TITLE, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun testHeadlineCardDoesNotExist() {
        setScreen()

        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_POST, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_POST_AUTHOR, useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_POST_CONTENT, useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_POST_IMAGE, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_POST_TITLE, useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_TOP_BAR, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_TOP_BAR_BUTTON, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_TOP_BAR_TITLE, useUnmergedTree = true).assertExists()
    }

    @Test
    fun testHeadlineCardIsClicked() {
        var wasActionReceived = false

        setScreen(navigateBack = {
            wasActionReceived = true
        })

        composeTestRule.onNodeWithTag(HeadlineIdentifier.INFO_SCREEN_TOP_BAR_BUTTON).assertIsDisplayed().performClick()

        assert(wasActionReceived)
    }

    private fun setScreen(headline: Headline = Headline(), navigateBack: () -> Unit = {}) {
        composeTestRule.setContent {
            HeadlineInfoScreen(
                headline = headline,
                navigateBack = navigateBack
            )
        }
    }
}