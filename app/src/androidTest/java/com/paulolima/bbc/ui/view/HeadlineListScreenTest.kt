package com.paulolima.bbc.ui.view

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.paulolima.bbc.data.model.Headline
import com.paulolima.bbc.ui.viewmodel.FakeViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HeadlineListScreenTest {

    @get:Rule(order = 2)
    val composeTestRule = createComposeRule()

    @Test
    fun testHeadlineCardIsDisplayed() {
        setScreen()

        composeTestRule.onNodeWithTag(HeadlineIdentifier.LIST_SCREEN).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HeadlineIdentifier.LIST_SCREEN_TOP_BAR, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun testHeadlineCardIsClicked() {
        var headline: Headline? = null

        setScreen(selectHeadline = {
            headline = it
        })

        composeTestRule.onNodeWithTag(HeadlineIdentifier.CARD).assertIsDisplayed().performClick()

        assertEquals(Headline(), headline)
    }

    private fun setScreen(selectHeadline: (Headline) -> Unit = {}) {
        composeTestRule.setContent {
            HeadlineListScreen(
                viewModel = FakeViewModel(),
                selectHeadline = { selectHeadline(it) }
            )
        }
    }
}