package com.example.zivameassignment.data.local.database.model

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
@Entity
class CartAdded(
                @PrimaryKey(autoGenerate = true)
                val id: Int = -1,
                val name: String = "",
                val image: String? = null,
                val price: String? = null,
                val rating: Int? = null) {
}