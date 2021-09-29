package com.example.zivameassignment.data.remote.service

import com.example.zivameassignment.data.remote.model.CartData
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
interface CartService {
@GET("db")
suspend fun fetchCartList(): Response<List<CartData>>

}