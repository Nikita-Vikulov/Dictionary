package com.example.history.interactor

import com.example.models.AppState
import com.example.models.DataModel
import com.example.models.IHistoryInteractor
import com.example.models.RepositoryLocal

class HistoryInteractor(
    private val repositoryLocal: RepositoryLocal<List<DataModel>>,
) : IHistoryInteractor {

    override suspend fun getData(): AppState {
        return AppState.Success(repositoryLocal.getData(""))
    }
}