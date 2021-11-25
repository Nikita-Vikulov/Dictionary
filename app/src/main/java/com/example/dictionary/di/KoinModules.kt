package com.example.dictionary.di

import androidx.room.Room
import com.example.data.*
import com.example.dictionary.interactor.main.MainInteractor
import com.example.dictionary.ui.main.MainViewModel
import com.example.history.interactor.HistoryInteractor
import com.example.history.ui.HistoryViewModel
import com.example.models.*
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDatabase::class.java, "HistoryDB").build() }
    single { get<HistoryDatabase>().historyDao() }

    single<DataSource<List<DataModel>>>() { RetrofitImplementation() }
    single<Repository<List<DataModel>>>() { RemoteRepoImpl(get()) }

    single<DataSourceLocal<List<DataModel>>>() { RoomDataSource(get()) }
    single<RepositoryLocal<List<DataModel>>>() { LocalRepoImpl(get()) }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory<IHistoryInteractor> { HistoryInteractor(get()) }
    factory { HistoryViewModel(get()) }
}