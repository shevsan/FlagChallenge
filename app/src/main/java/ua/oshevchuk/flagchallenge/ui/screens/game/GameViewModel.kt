package ua.oshevchuk.flagchallenge.ui.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.oshevchuk.flagchallenge.ui.entities.Country
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {
    private val _scoreState = MutableStateFlow(GameState())
    val scoreState: StateFlow<GameState> = _scoreState.asStateFlow()

    private val countries = Country.getCountries()

    init {
        startNewRound()
    }

    fun startNewRound() {

        val correctCountry = countries.random()
        val wrongCountry = countries.filter { it != correctCountry }.random()
        val answers = listOf(correctCountry.name, wrongCountry.name).shuffled()
        _scoreState.value = _scoreState.value.copy(
            currentFlag = correctCountry,
            answers = answers,
            isAnswerCorrect = null
        )

    }

    fun clearAnswerState() {
        _scoreState.value = _scoreState.value.copy(
            isAnswerCorrect = null
        )
    }

    fun onRestartGame() {
        _scoreState.value = _scoreState.value.copy(
            isAnswerCorrect = null,
            score = 0,
            round = 1
        )
        startNewRound()
    }

    fun submitAnswer(answer: String) {
        viewModelScope.launch {
            val isCorrect = answer == _scoreState.value.currentFlag.name
            val newScore = if (isCorrect) _scoreState.value.score + 1 else _scoreState.value.score
            val newRoundCount = _scoreState.value.round + 1

            if (_scoreState.value.round == 5) {
                _scoreState.value = _scoreState.value.copy(
                    score = newScore,
                    isAnswerCorrect = isCorrect,
                    isGameOver = true
                )
            } else {
                _scoreState.value = _scoreState.value.copy(
                    score = newScore,
                    isAnswerCorrect = isCorrect,
                    round = newRoundCount,
                )
            }
        }
    }
}