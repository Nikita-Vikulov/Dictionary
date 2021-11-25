package com.example.models

import com.google.gson.annotations.SerializedName


data class DataModel(
    @SerializedName("text")
    val text: String?,
    @SerializedName("meanings")
    val meaning: List<Meaning>?
)

/** Значение слова/фразы */
data class Meaning(
    @SerializedName("translation")
    val translation: Translation?,
    @SerializedName("imageUrl")
    val imageUrl: String?
)

/** Перевод слова/фразы */
data class Translation(
    @SerializedName("text")
    val translation: String?
)