package com.example.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.models.AppState

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    protected abstract val model: BaseViewModel<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model.getStateLiveData().observe(this) { renderData(it) }
    }

    abstract fun renderData(appState: T)
}