package com.example.zivameassignment.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.zivameassignment.R
import com.example.zivameassignment.data.dto.RecyclerItemListner
import com.example.zivameassignment.data.local.database.model.CartAdded
import com.example.zivameassignment.data.remote.model.CartData
import com.example.zivameassignment.databinding.CartItemsBinding
import com.squareup.picasso.Picasso

/**
 * Created by Utkarsh Sundaram on 29-09-2021.
 */
class CartAddedViewHolder(private val itemBinding: CartItemsBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(cartItems: CartData) {
        itemBinding.tvName.text = cartItems.name.toString()
        itemBinding.tvPrice.text = cartItems.price.toString()
        itemBinding.tvRating.text = cartItems.rating.toString()
        Picasso.get().load(cartItems.image_url).placeholder(R.drawable.ic_healthy_food)
            .error(R.drawable.ic_healthy_food).into(itemBinding.ivRecipeItemImage)
    }
}