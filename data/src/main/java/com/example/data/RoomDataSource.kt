package com.example.data

import com.example.models.AppState
import com.example.models.DataModel
import com.example.models.DataSourceLocal

class RoomDataSource(
    private val historyDao: HistoryDao,
) : DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return historyDao.all().map(HistoryEntity::toDomainModel)
    }

    override suspend fun saveData(appState: AppState) {
        val entity = appState.toEntity() ?: return
        historyDao.insert(entity)
    }
}