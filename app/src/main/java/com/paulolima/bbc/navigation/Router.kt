package com.paulolima.bbc.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paulolima.bbc.data.model.Headline
import com.paulolima.bbc.navigation.destination.Destination
import com.paulolima.bbc.ui.theme.BBCTheme
import com.paulolima.bbc.ui.view.HeadlineInfoScreen
import com.paulolima.bbc.ui.view.HeadlineListScreen
import com.paulolima.bbc.ui.view.LoginScreen

@Composable
fun Router() {
    val navController = rememberNavController()

    BBCTheme {
        Surface {
            NavHost(
                navController = navController,
                startDestination = Destination.Login.name,
            ) {
                composable(route = Destination.Login.name) {
                    LoginScreen(
                        loginButton = {
                            navController.navigate(Destination.HeadlineList.name)
                        }
                    )
                }
                composable(route = Destination.HeadlineList.name) {
                    HeadlineListScreen(
                        selectHeadline = { headline ->
                            navController.currentBackStackEntry?.savedStateHandle?.apply {
                                set("headline", headline)
                            }
                            navController.navigate(Destination.HeadlineInfo.name)
                        }
                    )
                }
                composable(route = Destination.HeadlineInfo.name) {
                    val headline = navController.previousBackStackEntry?.savedStateHandle?.get<Headline>(
                        "headline"
                    ) ?: return@composable

                    HeadlineInfoScreen(
                        headline = headline,
                        navigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
