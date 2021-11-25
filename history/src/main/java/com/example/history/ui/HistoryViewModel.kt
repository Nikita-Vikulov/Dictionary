package com.example.history.ui

import android.util.Log
import com.example.base.BaseViewModel
import com.example.models.AppState
import com.example.models.IHistoryInteractor
import kotlinx.coroutines.*


class HistoryViewModel(
    private val interactor: IHistoryInteractor,
) : BaseViewModel<AppState>() {

    private val viewModelScope = CoroutineScope(
        Dispatchers.Main +
            SupervisorJob()
            + CoroutineExceptionHandler { _, t ->
            Log.e(HistoryViewModel::class.java.simpleName, "error", t)
        }
    )

    init {
        getData()
    }

    fun getData() {
        stateLiveData.value = AppState.Loading(null)
        viewModelScope.coroutineContext.cancelChildren()
        viewModelScope.launch {
            val data = interactor.getData()
            stateLiveData.value = data
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
}