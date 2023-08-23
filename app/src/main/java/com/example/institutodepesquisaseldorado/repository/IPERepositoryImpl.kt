package com.example.institutodepesquisaseldorado.repository

import com.example.institutodepesquisaseldorado.service.ServiceApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IPERepositoryImpl @Inject constructor(
    private val api: ServiceApi,
) : IPERepository{

    override suspend fun getNowPlaying(language: String, page: Int)  = flow {
        emit(api.getNowPlaying(language, page))
    }
    override suspend fun getPopular(language: String, page: Int)     = flow {
        emit(api.getPopular(language, page))
    }
    override suspend fun getTopRated(language: String, page: Int)    = flow {
        emit(api.getTopRated(language, page))
    }
    override suspend fun getUpcoming(language: String, page: Int)    = flow {
        emit(api.getUpcoming(language, page))
    }

}