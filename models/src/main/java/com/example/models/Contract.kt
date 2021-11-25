package com.example.models

sealed interface AppState {
    data class Success(val data: List<DataModel>) : AppState
    data class Error(val t: Throwable) : AppState
    data class Loading(val progress: Int? = null) : AppState
}

interface View {

    fun renderData(appState: AppState)
}

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}


interface Interactor<T> {

    suspend fun getData(word: String, isRemoteSource: Boolean): T
}

interface IHistoryInteractor {

    suspend fun getData(): AppState
}

interface Repository<T> {

    suspend fun getData(word: String): T
}

interface DataSource<T> {

    suspend fun getData(word: String): T
}

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveData(appState: AppState)
}

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveData(appState: AppState)
}