package com.example.dictionary

import android.app.Application
import com.example.dictionary.di.application
import com.example.dictionary.di.mainScreen
import org.koin.core.context.startKoin


class TranslatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(listOf(application, mainScreen))
        }
    }
}