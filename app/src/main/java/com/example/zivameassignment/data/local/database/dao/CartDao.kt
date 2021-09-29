package com.example.zivameassignment.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zivameassignment.data.local.database.model.CartAdded

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
@Dao
interface CartDao {
    @Query("SELECT * FROM CartAdded")
    fun getAllCartData(): List<CartAdded>

    @Insert
    fun insertCartItems(cartItems: CartAdded)

    @Query("DELETE FROM CartAdded")
    fun nukeTable()
}