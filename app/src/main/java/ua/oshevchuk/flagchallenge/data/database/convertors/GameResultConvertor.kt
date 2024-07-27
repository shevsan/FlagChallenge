package ua.oshevchuk.flagchallenge.data.database.convertors

import ua.oshevchuk.flagchallenge.data.database.entities.GameResultDO
import ua.oshevchuk.flagchallenge.ui.entities.GameResult

fun GameResult.toDO() = GameResultDO(
    timestamp = this.timestamp,
    score = this.score
)

fun GameResultDO.toEntity() = GameResult(
    timestamp = this.timestamp ?: 0,
    score = this.score ?: 0
)