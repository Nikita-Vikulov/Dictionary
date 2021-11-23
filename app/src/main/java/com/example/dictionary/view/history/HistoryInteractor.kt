package com.example.dictionary.view.history

import com.example.dictionary.model.AppState
import com.example.dictionary.model.DataModel
import com.example.dictionary.model.Repository
import com.example.dictionary.model.room.RepositoryLocal
import com.example.dictionary.presenter.Interactor

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState{
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
