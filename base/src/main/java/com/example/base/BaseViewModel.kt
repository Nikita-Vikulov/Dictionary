package com.example.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.models.AppState

abstract class BaseViewModel<T : AppState>(
    protected val stateLiveData: MutableLiveData<T> = MutableLiveData(),
) : ViewModel() {

    fun getStateLiveData(): LiveData<T> = stateLiveData

    override fun onCleared() {
    }
}