package com.example.zivameassignment.data.local.database

import android.media.Image
import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun imageToString(image: Image?): String? {
            return image?.toString()
        }

        @TypeConverter
        @JvmStatic
        fun stringToImage(jsonString: String?): Image? {
            return if (jsonString != null) Gson().fromJson(jsonString, Image::class.java) else null
        }
    }
}