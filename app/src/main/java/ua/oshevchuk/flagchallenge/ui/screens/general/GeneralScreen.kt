package ua.oshevchuk.flagchallenge.ui.screens.general

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
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
import ua.oshevchuk.flagchallenge.ui.theme.Purple80

@Composable
fun GeneralScreen(
    modifier: Modifier = Modifier,
    onStartClicked: () -> Unit,
    viewModel: GeneralViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getResult()
    }

    val lastResult = remember {
        mutableStateOf<GameResult?>(null)
    }
    when (val getLastResultState = viewModel.getResultState.collectAsState().value) {
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

        is Response.Success -> {
            lastResult.value = getLastResultState.data
        }

        else -> {}
    }

    GeneralScreenContent(
        modifier = modifier,
        onStartClicked = onStartClicked,
        lastResult = lastResult
    )
}

@Composable
fun GeneralScreenContent(
    modifier: Modifier = Modifier,
    onStartClicked: () -> Unit,
    lastResult: MutableState<GameResult?>
) {
    Box(
        modifier = modifier,
    ) {
        if ((lastResult.value?.timestamp ?: 0) != 0L)
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                colors = CardDefaults.cardColors(containerColor = Purple80)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ) {
                    Text(
                        text = "${stringResource(id = R.string.ur_last_score)} ${lastResult.value?.score}",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${lastResult.value?.getTime()} ${lastResult.value?.getDate()}",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }


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
        LottieAnimation(
            composition = preloaderLottieComposition,
            progress = preloaderProgress,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 70.dp)
                .align(BottomCenter),
            shape = RoundedCornerShape(20.dp),
            onClick = onStartClicked
        ) {
            Text(
                text = stringResource(id = R.string.start),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 32.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GeneralScreenContentPreview() {
    GeneralScreenContent(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp, vertical = 10.dp),
        onStartClicked = {},
        lastResult = remember {
            mutableStateOf(null)
        })
}