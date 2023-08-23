package com.example.institutodepesquisaseldorado.repository

import com.example.institutodepesquisaseldorado.data.model.movie.MovieModelResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IPERepository{
    suspend fun getNowPlaying(language: String = "en-US", page: Int) : Flow<Response<MovieModelResponse>>
    suspend fun getPopular(language: String = "en-US", page: Int) : Flow<Response<MovieModelResponse>>
    suspend fun getTopRated(language: String = "en-US", page: Int) : Flow<Response<MovieModelResponse>>
    suspend fun getUpcoming(language: String = "en-US", page: Int) : Flow<Response<MovieModelResponse>>
}
