package ua.oshevchuk.flagchallenge.ui.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import ua.oshevchuk.flagchallenge.R

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    viewModel: GameViewModel = hiltViewModel(),
    onSuccess: () -> Unit,
    onFailure: () -> Unit,
    onGameOver: (Int) -> Unit
) {
    val gameState = viewModel.scoreState.collectAsState().value

    LaunchedEffect(gameState.isAnswerCorrect) {
        gameState.isAnswerCorrect?.let {
            viewModel.clearAnswerState()
            if (it) {
                onSuccess()
            } else {
                onFailure()
            }
            viewModel.startNewRound()
        }
    }

    LaunchedEffect(gameState.isGameOver) {
        if (gameState.isGameOver) {
            onGameOver(gameState.score)
            viewModel.onRestartGame()
        }
    }

    GameScreenContent(
        modifier = modifier,
        onBackClicked = onBackClicked,
        gameState = gameState,
        submitAnswer = viewModel::submitAnswer,
        onRestart = viewModel::onRestartGame
    )
}

@Composable
fun GameScreenContent(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    gameState: GameState,
    submitAnswer: (String) -> Unit,
    onRestart: () -> Unit
) {
    Box(modifier = modifier) {
        IconButton(
            onClick = onBackClicked, modifier = Modifier
                .size(25.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = stringResource(
                    id = R.string.arrow_back
                )
            )
        }
        IconButton(
            onClick = onRestart, modifier = Modifier
                .size(25.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_reset),
                contentDescription = stringResource(
                    id = R.string.arrow_back
                )
            )
        }

        Text(
            text = "${gameState.round}/5 ${stringResource(id = R.string.round)}",
            fontSize = 28.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.TopCenter),
            textAlign = TextAlign.Center
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Image(
                painter = rememberAsyncImagePainter(gameState.currentFlag.flagUrl),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(shape = RoundedCornerShape(20.dp), onClick = {
                    submitAnswer(gameState.answers.first())
                }) {
                    Text(text = gameState.answers.first(), color = Color.White)
                }
                Button(shape = RoundedCornerShape(20.dp), onClick = {
                    submitAnswer(gameState.answers.last())
                }) {
                    Text(text = gameState.answers.last(), color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GameScreenContentPreview() {
    GameScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        onBackClicked = {},
        gameState = GameState(),
        submitAnswer = {},
        onRestart = {}
    )
}