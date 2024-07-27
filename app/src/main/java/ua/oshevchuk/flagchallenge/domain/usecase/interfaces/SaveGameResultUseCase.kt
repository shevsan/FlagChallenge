package ua.oshevchuk.flagchallenge.domain.usecase.interfaces

import ua.oshevchuk.flagchallenge.ui.entities.GameResult

interface SaveGameResultUseCase {
    suspend operator fun invoke(result: GameResult)
}