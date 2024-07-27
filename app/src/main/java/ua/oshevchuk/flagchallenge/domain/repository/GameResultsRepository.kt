package ua.oshevchuk.flagchallenge.domain.repository

import ua.oshevchuk.flagchallenge.ui.entities.GameResult

interface GameResultsRepository {
    suspend fun insertResult(result : GameResult)
    suspend fun getLastResult() : GameResult
}