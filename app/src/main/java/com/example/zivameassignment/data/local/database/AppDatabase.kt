package com.example.zivameassignment.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.zivameassignment.data.local.database.dao.CartDao
import com.example.zivameassignment.data.local.database.model.CartAdded

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
@Database(entities = [CartAdded::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}