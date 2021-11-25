package com.example.dictionary.ui.main

import com.example.base.BaseViewModel
import com.example.dictionary.interactor.main.MainInteractor
import com.example.models.AppState
import kotlinx.coroutines.*

class MainViewModel(
    private val interactor: MainInteractor,
) : BaseViewModel<AppState>() {

    private val viewModelScope = CoroutineScope(
        Dispatchers.Main +
                SupervisorJob()
    )

    fun getWordDescriptions(word: String, isOnline: Boolean) {
        viewModelScope.launch {
            try {
                val data = interactor.getData(word, isOnline)
                stateLiveData.value = data
            } catch (e: Exception) {
                stateLiveData.value = AppState.Error(e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}