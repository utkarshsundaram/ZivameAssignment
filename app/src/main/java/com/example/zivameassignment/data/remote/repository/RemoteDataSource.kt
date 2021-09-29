package com.example.zivameassignment.data.remote.repository

import com.example.zivameassignment.data.local.Resource
import com.example.zivameassignment.data.remote.model.CartData
import com.example.zivameassignment.data.remote.model.CartResponse

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
interface RemoteDataSource {
    suspend fun requestCartData(): Resource<CartResponse>
}