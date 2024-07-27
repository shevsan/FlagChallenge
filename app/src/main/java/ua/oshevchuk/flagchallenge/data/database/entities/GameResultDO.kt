package ua.oshevchuk.flagchallenge.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_results")
data class GameResultDO(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val timestamp : Long? = 0,
    val score : Int? = null
)