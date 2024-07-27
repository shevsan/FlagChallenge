package ua.oshevchuk.flagchallenge.ui.entities

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

data class GameResult(
    val timestamp : Long,
    val score : Int
){
    @SuppressLint("SimpleDateFormat")
    fun getDate(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val netDate = Date(timestamp)
        return sdf.format(netDate)
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime(): String {
        val sdf = SimpleDateFormat("HH:mm")
        val netDate = Date(timestamp)
        return sdf.format(netDate)
    }
}
