package ua.oshevchuk.flagchallenge.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ua.oshevchuk.flagchallenge.ui.screens.game.GameScreen
import ua.oshevchuk.flagchallenge.ui.screens.game.AnswerResultScreen
import ua.oshevchuk.flagchallenge.ui.screens.general.GeneralScreen
import ua.oshevchuk.flagchallenge.ui.screens.results.GameResultsScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = GeneralScreen.route
    ) {
        GeneralScreen.get(
            navGraphBuilder = this,
            onRedirectToGameScreen = {
                navController.navigate(GameScreen.route)
            }
        )
        GameScreen.get(navGraphBuilder = this, onBackClicked = {
            navController.navigateUp()
        }, onSuccess = {
            navController.navigate(SuccessScreen.route)
        }, onFailure = {
            navController.navigate(FailureScreen.route)
        },
            onRedirectToGameResults = {
                navController.navigate("${CoreFlowRoute.GAME_RESULTS.route}/$it") {
                    popUpToInclusive(GameScreen.route)
                }
            })

        SuccessScreen.get(navGraphBuilder = this, onNext = {
            navController.navigateUp()
        })

        FailureScreen.get(navGraphBuilder = this, onNext = {
            navController.navigateUp()
        })

        GameResultsScreen.get(navGraphBuilder = this, onRedirectToGeneral = {
            navController.navigate(GeneralScreen.route) {
                popUpToInclusive(GameResultsScreen.route)
            }
        })
    }
}

object GeneralScreen : Screen(CoreFlowRoute.GENERAL.route) {
    fun get(
        navGraphBuilder: NavGraphBuilder,
        onRedirectToGameScreen: () -> Unit
    ) {
        navGraphBuilder.composable(GeneralScreen.route) {
            GeneralScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                onStartClicked = onRedirectToGameScreen
            )
        }
    }
}

object GameScreen : Screen(CoreFlowRoute.GAME.route) {
    fun get(
        navGraphBuilder: NavGraphBuilder,
        onBackClicked: () -> Unit,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
        onRedirectToGameResults: (Int) -> Unit
    ) {
        navGraphBuilder.composable(GameScreen.route) {
            GameScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                onBackClicked = onBackClicked,
                onSuccess = onSuccess,
                onFailure = onFailure,
                onGameOver = onRedirectToGameResults
            )
        }
    }
}

object SuccessScreen : Screen(CoreFlowRoute.GAME_SUCCESS.route) {
    fun get(navGraphBuilder: NavGraphBuilder, onNext: () -> Unit) {
        navGraphBuilder.composable(SuccessScreen.route) {
            AnswerResultScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                onNext = onNext,
                isSuccess = true
            )
        }
    }
}

object FailureScreen : Screen(CoreFlowRoute.GAME_FAILURE.route) {
    fun get(navGraphBuilder: NavGraphBuilder, onNext: () -> Unit) {
        navGraphBuilder.composable(FailureScreen.route) {
            AnswerResultScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                onNext = onNext,
                isSuccess = false
            )
        }
    }
}

object GameResultsScreen : Screen("${CoreFlowRoute.GAME_RESULTS.route}/{score}") {
    fun get(navGraphBuilder: NavGraphBuilder, onRedirectToGeneral: () -> Unit) {
        navGraphBuilder.composable(route = GameResultsScreen.route, arguments = listOf(
            navArgument("score") { type = NavType.IntType }
        )) {
            val score = it.arguments?.getInt("score") ?: 0
            GameResultsScreen(
                modifier = Modifier.fillMaxSize(),
                onDoneClicked = onRedirectToGeneral,
                score = score
            )
        }
    }
}


fun NavOptionsBuilder.popUpToInclusive(route: String) {
    popUpTo(route) {
        inclusive = true
    }
}