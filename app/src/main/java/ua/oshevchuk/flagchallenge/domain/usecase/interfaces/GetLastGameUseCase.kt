package ua.oshevchuk.flagchallenge.domain.usecase.interfaces

import ua.oshevchuk.flagchallenge.ui.entities.GameResult

interface GetLastGameUseCase {
    suspend operator fun invoke() : GameResult
}