package com.example.zivameassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zivameassignment.data.dto.RecyclerItemListner
import com.example.zivameassignment.data.local.database.model.CartAdded
import com.example.zivameassignment.data.remote.model.CartData
import com.example.zivameassignment.databinding.CartItemsBinding
import com.example.zivameassignment.ui.cartItemListing.CartListViewModel
import com.example.zivameassignment.ui.cartadded.CartAddedViewModel

/**
 * Created by Utkarsh Sundaram on 29-09-2021.
 */
class CartAddedAdapter(private val cartAddedViewModel: CartAddedViewModel, private val cartData: List<CartData>) : RecyclerView.Adapter<CartAddedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAddedViewHolder {
        val itemBinding = CartItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartAddedViewHolder(itemBinding)
    }
    override fun getItemCount(): Int {
        return cartData.size
    }

    override fun onBindViewHolder(holder: CartAddedViewHolder, position: Int) {
        holder.bind(cartData[position])
    }
}