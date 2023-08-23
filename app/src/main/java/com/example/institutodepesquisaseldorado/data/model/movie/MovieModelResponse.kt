package com.example.institutodepesquisaseldorado.data.model.movie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieModelResponse(
    @SerializedName("results")
    val results: List<MovieModelData>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("dates")
    val dates: DatesModelData,
) : Serializable
