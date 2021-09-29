package com.example.zivameassignment.data.local.database.model

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
@Entity
 data class CartAdded(
                @PrimaryKey(autoGenerate = true)
                val id:Int,
                val name: String = "",
                val image: String? = null,
                val price: String? = null,
                val rating: Int? = null)
{

}