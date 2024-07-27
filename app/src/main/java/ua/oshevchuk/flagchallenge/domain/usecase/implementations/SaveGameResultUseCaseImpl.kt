package ua.oshevchuk.flagchallenge.domain.usecase.implementations

import ua.oshevchuk.flagchallenge.domain.repository.GameResultsRepository
import ua.oshevchuk.flagchallenge.domain.usecase.interfaces.SaveGameResultUseCase
import ua.oshevchuk.flagchallenge.ui.entities.GameResult
import javax.inject.Inject

class SaveGameResultUseCaseImpl @Inject constructor(
    private val repository: GameResultsRepository
) : SaveGameResultUseCase {
    override suspend fun invoke(result: GameResult) {
      repository.insertResult(result)
    }
}