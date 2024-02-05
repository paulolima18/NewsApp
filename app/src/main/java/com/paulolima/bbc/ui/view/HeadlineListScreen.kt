package com.paulolima.bbc.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulolima.bbc.R
import com.paulolima.bbc.data.model.Headline
import com.paulolima.bbc.ui.viewmodel.MainViewModel
import com.paulolima.bbc.ui.viewmodel.MainViewModelInterface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadlineListScreen(
    viewModel: MainViewModelInterface = hiltViewModel<MainViewModel>(),
    selectHeadline: (Headline) -> Unit
) {
    val state = viewModel.uiState.collectAsState().value

    Scaffold(
        modifier = Modifier.testTag(HeadlineIdentifier.LIST_SCREEN),
        topBar = {
            TopAppBar(
                modifier = Modifier.testTag(HeadlineIdentifier.LIST_SCREEN_TOP_BAR),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(text = stringResource(id = R.string.headline_list_title)) }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(items = state.headlines) { headline ->
                HeadlineCard(
                    headline = headline,
                    onClick = { selectHeadline(headline) }
                )
            }
        }
    }

}