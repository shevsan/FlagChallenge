package ua.oshevchuk.flagchallenge.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ua.oshevchuk.flagchallenge.data.database.convertors.toDO
import ua.oshevchuk.flagchallenge.data.database.convertors.toEntity
import ua.oshevchuk.flagchallenge.data.database.dao.GameResultsDao
import ua.oshevchuk.flagchallenge.di.annotations.IoDispatcher
import ua.oshevchuk.flagchallenge.domain.repository.GameResultsRepository
import ua.oshevchuk.flagchallenge.ui.entities.GameResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameResultsRepositoryImpl @Inject constructor(
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val dao: GameResultsDao
) : GameResultsRepository {
    override suspend fun insertResult(result: GameResult) {
        withContext(ioDispatcher) {
            dao.insertResult(result.toDO())
        }
    }

    override suspend fun getLastResult(): GameResult =
        withContext(ioDispatcher) {
            dao.getLastGame()?.toEntity() ?: GameResult(0, 0)
        }

}