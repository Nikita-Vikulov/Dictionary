package com.example.dictionary.di

import androidx.room.Room
import com.example.dictionary.model.*
import com.example.dictionary.model.room.HistoryDataBase
import com.example.dictionary.model.room.RepositoryImplementationLocal
import com.example.dictionary.model.room.RepositoryLocal
import com.example.dictionary.view.history.HistoryInteractor
import com.example.dictionary.view.history.HistoryViewModel
import com.example.dictionary.view.MainInteractor
import com.example.dictionary.view.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(
        RoomDataBaseImplementation(get())
    )
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}