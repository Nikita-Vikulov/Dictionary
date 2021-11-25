package com.example.dictionary.interactor.main

import com.example.models.*

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, isRemoteSource: Boolean): AppState {
        return if (isRemoteSource) {
            val data = remoteRepository.getData(word)
            val appState = AppState.Success(data)
            localRepository.saveData(appState)
            appState
        } else {
            val data = localRepository.getData(word)
            AppState.Success(data)
        }
    }
}
