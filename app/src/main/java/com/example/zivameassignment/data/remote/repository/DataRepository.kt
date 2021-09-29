package com.example.zivameassignment.data.remote.repository

import com.example.zivameassignment.data.local.Resource
import com.example.zivameassignment.data.remote.RemoteData
import com.example.zivameassignment.data.remote.model.CartData
import com.example.zivameassignment.data.remote.model.CartResponse
import com.task.data.local.LocalData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
class DataRepository @Inject constructor(private val remoteRepository: RemoteData, private val localRepository: LocalData, private val ioDispatcher: CoroutineContext) : DataRepositorySource {
    override suspend fun requestCartItems(): Flow<Resource<CartResponse>> {
        return flow {
            emit(remoteRepository.requestCartData())
        }.flowOn(ioDispatcher)
    }
}