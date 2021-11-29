package com.example.dictionary.di

import androidx.room.Room
import com.example.dictionary.ui.main.MainActivity
import com.example.dictionary.ui.main.MainInteractor
import com.example.dictionary.ui.main.MainViewModel
import com.example.historyscreen.history.HistoryActivity
import com.example.historyscreen.history.HistoryInteractor
import com.example.historyscreen.history.HistoryViewModel
import com.example.model.dto.SearchResultDto
import com.example.repository.*
import com.example.repository.room.HistoryDataBase
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.core.qualifier.named
import org.koin.dsl.module


val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<SearchResultDto>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<SearchResultDto>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
}
