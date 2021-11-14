package com.example.dictionary.view

import com.example.dictionary.presenter.Interactor
import com.example.dictionary.model.AppState
import com.example.dictionary.model.DataModel
import com.example.dictionary.model.Repository

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState.Success {
        return AppState.Success(
            if (fromRemoteSource) {
            remoteRepository
        } else {
            localRepository
        }.getData(word)
        )
    }
}