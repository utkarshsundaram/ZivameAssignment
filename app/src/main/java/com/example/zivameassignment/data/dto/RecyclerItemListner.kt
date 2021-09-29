package com.example.zivameassignment.data.dto

import com.example.zivameassignment.data.remote.model.CartData

/**
 * Created by Utkarsh Sundaram on 29-09-2021.
 */
interface RecyclerItemListner {
    fun onItemSelected(recipe : CartData)
}