package com.example.dictionary.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel <T : AppState>(
   protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : ViewModel() {
    open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveDataForViewToObserve
    override fun onCleared() {
        compositeDisposable.clear()
    }
}
