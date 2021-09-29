package com.example.zivameassignment.errors

import com.example.zivameassignment.data.remote.error.mapper.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
