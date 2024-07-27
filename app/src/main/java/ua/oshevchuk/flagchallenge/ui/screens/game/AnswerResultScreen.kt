package ua.oshevchuk.flagchallenge.ui.screens.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import ua.oshevchuk.flagchallenge.R
import ua.oshevchuk.flagchallenge.ui.theme.Purple80
import kotlin.time.Duration.Companion.seconds

@Composable
fun AnswerResultScreen(modifier: Modifier = Modifier, isSuccess: Boolean, onNext: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2.seconds)
        onNext()
    }
    AnswerResultScreenContent(modifier, isSuccess)
}

@Composable
fun AnswerResultScreenContent(modifier: Modifier = Modifier, isSuccess: Boolean) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            if (isSuccess) R.raw.success else R.raw.failure
        )
    )
    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = if (isSuccess) R.string.success else R.string.failure),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Purple80,
                textAlign = TextAlign.Center
            )
            LottieAnimation(
                composition = preloaderLottieComposition,
                progress = preloaderProgress,
                modifier = Modifier.size(200.dp)
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AnswerResultScreenContentPreview() {
    AnswerResultScreenContent(modifier = Modifier.fillMaxSize(), isSuccess = false)
}