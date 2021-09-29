package com.example.zivameassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zivameassignment.data.dto.RecyclerItemListner
import com.example.zivameassignment.data.remote.model.CartData
import com.example.zivameassignment.databinding.CartItemsBinding
import com.example.zivameassignment.ui.base.cartItemListing.CartListActivity
import com.example.zivameassignment.ui.cartItemListing.CartListViewModel

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
class CartListAdapter(private val cartListViewModel: CartListViewModel, private val cartData: List<CartData>) : RecyclerView.Adapter<CartListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        val itemBinding = CartItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartListViewHolder(itemBinding)
    }
    private val onItemClickListener: RecyclerItemListner = object : RecyclerItemListner {
        override fun onItemSelected(cartData: CartData) {
            cartListViewModel.saveCartItems(cartData)
        }
    }
    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        holder.bind(cartData[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
       return cartData.size
    }
}