package com.task.di

import android.content.Context
import com.example.zivameassignment.data.remote.error.mapper.ErrorMapper
import com.example.zivameassignment.utils.Network

import com.example.zivameassignment.utils.NetworkConnectivity

import com.task.data.local.LocalData

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideLocalRepository(@ApplicationContext context: Context): LocalData {
        return LocalData(context)
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }
    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivity {
        return Network(context)
    }


}
