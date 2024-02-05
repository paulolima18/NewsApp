package com.paulolima.bbc.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.paulolima.bbc.data.model.Headline

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun HeadlineCard(
    headline: Headline,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .testTag(HeadlineIdentifier.CARD)
            .fillMaxWidth()
            .padding(16.dp),
        onClick = onClick
    ) {
        Card(modifier = Modifier.padding(16.dp)) {
            headline.source?.name?.let {
                Text(
                    modifier = Modifier.testTag(HeadlineIdentifier.CARD_SOURCE_TEXT),
                    text = it,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            headline.title?.let {
                Text(
                    modifier = Modifier.testTag(HeadlineIdentifier.CARD_TITLE_TEXT),
                    text = it
                )
            }
            headline.urlToImage?.let {
                GlideImage(
                    modifier = Modifier.testTag(HeadlineIdentifier.CARD_IMAGE),
                    model = it,
                    contentDescription = HeadlineIdentifier.CARD_IMAGE
                )
            }
            headline.publishedAt?.let {
                Text(
                    modifier = Modifier.testTag(HeadlineIdentifier.CARD_DATE_TEXT),
                    text = it
                )
            }
        }
    }
}