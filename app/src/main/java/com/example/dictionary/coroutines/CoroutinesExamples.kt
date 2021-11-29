package com.example.dictionary.coroutines

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val scope = CoroutineScope(Dispatchers.IO)

suspend fun longRunningOperation(): String {
    Log.d("Coroutines", "longRunningOperation началась")
    scope.launch {
        Log.d("Coroutines", "внутренний launch")
    }
    delay(1000)
    Log.d("Coroutines", "longRunningOperation закончилась")
    return "abc"
}

fun coroutinesExample() {
    scope.launch {
        val stringDeferred = scope.async {
            longRunningOperation()
        }

        val string = stringDeferred.await()
        Log.d("Coroutines", "Вернулось значение $string")
    }
    onDestroy()
}

fun onDestroy() {
}