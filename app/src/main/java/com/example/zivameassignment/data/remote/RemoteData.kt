package com.example.zivameassignment.data.remote

import com.example.zivameassignment.data.local.Resource
import com.example.zivameassignment.data.remote.error.mapper.NETWORK_ERROR
import com.example.zivameassignment.data.remote.error.mapper.NO_INTERNET_CONNECTION
import com.example.zivameassignment.data.remote.model.CartData
import com.example.zivameassignment.data.remote.model.CartResponse
import com.example.zivameassignment.data.remote.repository.RemoteDataSource
import com.example.zivameassignment.data.remote.service.CartService
import com.example.zivameassignment.data.remote.service.ServiceGenerator
import com.example.zivameassignment.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
class RemoteData @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity) : RemoteDataSource {
    override suspend fun requestCartData(): Resource<CartResponse> {
        val cartService = serviceGenerator.createService(CartService::class.java)
        return when (val response = processCall(cartService::fetchCartList)) {
            is List<*> -> {
                Resource.Success(data = CartResponse(response as ArrayList<CartData>))
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
