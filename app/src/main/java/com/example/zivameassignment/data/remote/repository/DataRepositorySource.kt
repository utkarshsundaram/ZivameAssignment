package com.example.zivameassignment.data.remote.repository

import com.example.zivameassignment.data.local.Resource
import com.example.zivameassignment.data.remote.model.CartData
import com.example.zivameassignment.data.remote.model.CartResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
interface DataRepositorySource {
    suspend fun requestCartItems(): Flow<Resource<CartResponse>>
}