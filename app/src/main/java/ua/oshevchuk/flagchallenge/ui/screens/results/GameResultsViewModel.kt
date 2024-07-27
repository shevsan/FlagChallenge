package ua.oshevchuk.flagchallenge.ui.screens.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.oshevchuk.flagchallenge.common.Response
import ua.oshevchuk.flagchallenge.common.executeSafely
import ua.oshevchuk.flagchallenge.domain.usecase.interfaces.SaveGameResultUseCase
import ua.oshevchuk.flagchallenge.ui.entities.GameResult
import javax.inject.Inject

@HiltViewModel
class GameResultsViewModel @Inject constructor(
    private val saveGameResultUseCase: SaveGameResultUseCase
) : ViewModel() {
    private val _insertResultState = MutableStateFlow<Response<Unit>?>(null)
    val insertResultState = _insertResultState.asStateFlow()

    fun insertResult(result: GameResult) {
        viewModelScope.launch {
            executeSafely {
                _insertResultState.emit(Response.Loading())
                saveGameResultUseCase(result)
            }.onSuccess {
                _insertResultState.emit(Response.Success(Unit))
            }.onFailure {
                _insertResultState.emit(Response.Error(it.message ?: ""))
            }
        }
    }
}