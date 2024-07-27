package ua.oshevchuk.flagchallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.oshevchuk.flagchallenge.data.database.dao.GameResultsDao
import ua.oshevchuk.flagchallenge.data.database.entities.GameResultDO

@Database(entities = [GameResultDO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameResultsDao(): GameResultsDao
}