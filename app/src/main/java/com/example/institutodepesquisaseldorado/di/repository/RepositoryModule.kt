package com.example.institutodepesquisaseldorado.di.repository

import com.example.institutodepesquisaseldorado.repository.IPERepository
import com.example.institutodepesquisaseldorado.repository.IPERepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: IPERepositoryImpl): IPERepository
}