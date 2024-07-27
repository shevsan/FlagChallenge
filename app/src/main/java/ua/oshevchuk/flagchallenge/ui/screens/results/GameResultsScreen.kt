package ua.oshevchuk.flagchallenge.ui.screens.results

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import ua.oshevchuk.flagchallenge.R
import ua.oshevchuk.flagchallenge.common.Response
import ua.oshevchuk.flagchallenge.ui.components.DotLoader
import ua.oshevchuk.flagchallenge.ui.entities.GameResult

@Composable
fun GameResultsScreen(
    modifier: Modifier = Modifier,
    onDoneClicked: () -> Unit,
    score: Int,
    viewModel: GameResultsViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.insertResult(
            GameResult(
                score = score,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    when (viewModel.insertResultState.collectAsState().value) {
        is Response.Error -> {
            val context = LocalContext.current
            val errorText = stringResource(id = R.string.smth_went_wrong)
            LaunchedEffect(Unit) {
                Toast.makeText(
                    context,
                    errorText,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        is Response.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                DotLoader()
            }
        }

        else -> {}
    }
    GameResultsScreenContent(modifier = modifier, onDoneClicked = onDoneClicked, score = score)
}

@Composable
fun GameResultsScreenContent(modifier: Modifier = Modifier, onDoneClicked: () -> Unit, score: Int) {
    Box(modifier = modifier) {
        val preloaderLottieComposition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                R.raw.globe_general
            )
        )
        val preloaderProgress by animateLottieCompositionAsState(
            preloaderLottieComposition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true
        )
        Text(
            text = "${stringResource(id = R.string.ur_score)} $score",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 32.sp,
            modifier = Modifier.align(TopCenter)
        )
        LottieAnimation(
            composition = preloaderLottieComposition,
            progress = preloaderProgress,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.Center)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 70.dp)
                .align(BottomCenter)
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = onDoneClicked
        ) {
            Text(
                text = stringResource(id = R.string.done),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 32.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GameResultsScreenContentPreview() {
    GameResultsScreenContent(modifier = Modifier.fillMaxSize(), onDoneClicked = {}, score = 3)
}