package com.example.zivameassignment.di

import com.example.zivameassignment.data.remote.error.mapper.ErrorMapper
import com.example.zivameassignment.data.remote.error.mapper.ErrorMapperSource

import com.example.zivameassignment.errors.ErrorUseCase
import com.example.zivameassignment.errors.ErrorManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// with @Module we Telling Dagger that, this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}
