package com.example.zivameassignment.data.remote.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
@JsonClass(generateAdapter = false)
@Parcelize
data class CartResponse(val products: List<CartData>): Parcelable {
}