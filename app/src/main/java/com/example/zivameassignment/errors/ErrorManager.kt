package com.example.zivameassignment.errors

import com.example.zivameassignment.data.remote.error.mapper.ErrorMapper
import javax.inject.Inject
import com.example.zivameassignment.data.remote.error.mapper.Error
/**
 * Created by Utkarsh Sundaram
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
