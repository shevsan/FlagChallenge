package ua.oshevchuk.flagchallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ua.oshevchuk.flagchallenge.data.database.entities.GameResultDO

@Dao
interface GameResultsDao {
    @Upsert
    suspend fun insertResult(gameResultDO: GameResultDO)

    @Query("SELECT * FROM game_results ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLastGame() : GameResultDO?

}