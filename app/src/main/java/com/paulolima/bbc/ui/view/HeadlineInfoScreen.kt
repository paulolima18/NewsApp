package com.paulolima.bbc.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.paulolima.bbc.R
import com.paulolima.bbc.data.model.Headline

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadlineInfoScreen(
    headline: Headline = Headline(),
    navigateBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.testTag(HeadlineIdentifier.INFO_SCREEN),
        topBar = {
            TopAppBar(
                modifier = Modifier.testTag(HeadlineIdentifier.INFO_SCREEN_TOP_BAR),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        modifier = Modifier.testTag(HeadlineIdentifier.INFO_SCREEN_TOP_BAR_TITLE),
                        text = headline.source?.name ?: stringResource(id = R.string.headline_info_title)
                    )
                },
                navigationIcon = {
                    IconButton(modifier = Modifier.testTag(HeadlineIdentifier.INFO_SCREEN_TOP_BAR_BUTTON), onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .testTag(HeadlineIdentifier.INFO_SCREEN_POST)
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PostContent(
                headline
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PostContent(
    post: Headline,
) {
    GlideImage(
        modifier = Modifier.testTag(HeadlineIdentifier.INFO_SCREEN_POST_IMAGE),
        model = post.urlToImage,
        contentDescription = "",
        contentScale = ContentScale.FillWidth
    )
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        post.title?.let {
            Text(
                modifier = Modifier.testTag(HeadlineIdentifier.INFO_SCREEN_POST_TITLE),
                text = it,
                style = MaterialTheme.typography.headlineLarge
            )
        }

        post.author?.let {
            Text(
                modifier = Modifier.testTag(HeadlineIdentifier.INFO_SCREEN_POST_AUTHOR),
                text = post.author,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        post.content?.let {
            Text(
                modifier = Modifier.testTag(HeadlineIdentifier.INFO_SCREEN_POST_CONTENT),
                text = it,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}