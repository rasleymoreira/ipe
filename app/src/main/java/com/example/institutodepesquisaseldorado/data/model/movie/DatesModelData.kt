package com.example.institutodepesquisaseldorado.data.model.movie

import com.google.gson.annotations.SerializedName

data class DatesModelData(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
)
