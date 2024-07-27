package ua.oshevchuk.flagchallenge.ui.screens.general

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.oshevchuk.flagchallenge.common.Response
import ua.oshevchuk.flagchallenge.common.executeSafely
import ua.oshevchuk.flagchallenge.domain.usecase.interfaces.GetLastGameUseCase
import ua.oshevchuk.flagchallenge.ui.entities.GameResult
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(private val getLastGameUseCase: GetLastGameUseCase) :
    ViewModel() {
    private val _getResultState = MutableStateFlow<Response<GameResult>?>(null)
    val getResultState = _getResultState.asStateFlow()

    fun getResult() {
        viewModelScope.launch {
            executeSafely {
                _getResultState.emit(Response.Loading())
                getLastGameUseCase()
            }.onSuccess {
                _getResultState.emit(Response.Success(it))
            }.onFailure {
                _getResultState.emit(Response.Error(it.message ?: ""))
            }
        }
    }
}