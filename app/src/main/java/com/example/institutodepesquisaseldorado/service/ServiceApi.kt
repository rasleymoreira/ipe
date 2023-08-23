package com.example.institutodepesquisaseldorado.service

import com.example.institutodepesquisaseldorado.data.model.movie.MovieModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("now_playing")
    suspend fun getNowPlaying(@Query("language") language: String , @Query("page") page: Int): Response<MovieModelResponse>

    @GET("popular")
    suspend fun getPopular(@Query("language") language: String, @Query("page") page: Int): Response<MovieModelResponse>

    @GET("top_rated")
    suspend fun getTopRated(@Query("language") language: String, @Query("page") page: Int): Response<MovieModelResponse>

    @GET("upcoming")
    suspend fun getUpcoming(@Query("language") language: String, @Query("page") page: Int): Response<MovieModelResponse>
}
