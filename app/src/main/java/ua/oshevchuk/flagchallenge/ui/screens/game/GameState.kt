package ua.oshevchuk.flagchallenge.ui.screens.game

import ua.oshevchuk.flagchallenge.ui.entities.Country

data class GameState(
    val currentFlag: Country = Country("", ""),
    val answers: List<String> = emptyList(),
    val score: Int = 0,
    val round: Int = 1,
    val isAnswerCorrect: Boolean? = null,
    val isGameOver : Boolean = false
)