package com.example.dictionary.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.dictionary.model.AppState
import com.example.dictionary.presenter.Presenter

abstract class BaseActivity<T : AppState, U> : AppCompatActivity() {
    abstract val model: MainViewModel
    abstract fun renderData(appState: T)
}