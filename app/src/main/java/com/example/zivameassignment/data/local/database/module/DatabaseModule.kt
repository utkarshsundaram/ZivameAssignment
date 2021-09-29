package com.example.zivameassignment.data.local.database.module

import android.content.Context
import androidx.room.Room
import com.example.zivameassignment.data.local.database.AppDatabase
import com.example.zivameassignment.data.local.database.dao.CartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideCartDao(appDatabase: AppDatabase): CartDao {
        return appDatabase.cartDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Assignment_Utkarsh_Sundaram"
        ).build()
    }
}