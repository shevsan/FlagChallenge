package ua.oshevchuk.flagchallenge.domain.usecase.implementations

import ua.oshevchuk.flagchallenge.domain.repository.GameResultsRepository
import ua.oshevchuk.flagchallenge.domain.usecase.interfaces.GetLastGameUseCase
import ua.oshevchuk.flagchallenge.ui.entities.GameResult
import javax.inject.Inject

class GetLastGameUseCaseImpl @Inject constructor(
    private val repository: GameResultsRepository
) : GetLastGameUseCase {

    override suspend fun invoke(): GameResult =
        repository.getLastResult()

}