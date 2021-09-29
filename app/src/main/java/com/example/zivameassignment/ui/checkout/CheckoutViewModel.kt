package com.example.zivameassignment.ui.checkout

import androidx.lifecycle.viewModelScope
import com.example.zivameassignment.data.local.database.dao.CartDao
import com.example.zivameassignment.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Utkarsh Sundaram on 29-09-2021.
 */
@HiltViewModel
class CheckoutViewModel@Inject constructor(private val cartDao: CartDao) : BaseViewModel() {
    fun clearTheData(){
        viewModelScope.launch {
            deleteTable()
        }

    }

    private suspend fun deleteTable() {
        withContext(Dispatchers.IO){
            cartDao.nukeTable()
        }
    }
}